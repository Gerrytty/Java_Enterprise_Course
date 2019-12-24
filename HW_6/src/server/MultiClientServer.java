package server;

import com.fasterxml.jackson.core.JsonProcessingException;
import data_base.DAO.*;
import data_base.DAO.ORM.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import commands.*;
import data_base.ORM.Message;
import data_base.ORM.Product;
import utils.JSON;
import utils.Password;
import utils.TokenUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class MultiClientServer {

    private ServerSocket serverSocket;
    private List<ClientHandler> clients;

    private UserDAO userDAO;
    private ObjectMapper mapper;

    public MultiClientServer() {
        clients = new ArrayList<>();
        userDAO = new UserDAO();
        mapper = new ObjectMapper();
    }

    public void start(int port) {

        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

        for(;;) {

            try {
                ClientHandler handler = new ClientHandler(serverSocket.accept());
                handler.start();

            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }
    }

    private class ClientHandler extends Thread {
        private Socket clientSocket;
        private BufferedReader reader;
        private PrintWriter writer;

        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
            clients.add(this);
            System.out.println("New client!");
        }

        @Override
        public void run() {

            System.out.println("in run");

            try {

                reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                writer = new PrintWriter(clientSocket.getOutputStream(), true);

                String line;

                User user = new User();
                UserDAO userDAO = new UserDAO();

                while ((line = reader.readLine()) != null) {

                    String messageToClient = "";

                    Request request = mapper.readValue(line, Request.class);
                    String header = request.getHeader();

                    System.out.println("Header = " + header);

                    if(header.equals("login")) {
                        login(line, user);
                    }

                    else if(header.equals("logout")) {
                        userDAO.delete(user);
                    }

                    else if(header.equals("message")) {
                        messageToClient = userMessage(line, user);
                        System.out.println(messageToClient);
                    }

                    else if(header.equals("add-product")) {
                        messageToClient = addProduct(line);
                    }

                    else if(header.equals("delete-product")) {
                        messageToClient = deleteProduct(line);
                    }

                    else if(header.equals("command")) {
                        messageToClient = listOfProducts(line);
                    }

                    for (ClientHandler client : clients) {

                        PrintWriter writer = new PrintWriter(client.clientSocket.getOutputStream(), true);

                        writer.println(messageToClient);
                    }
                }

                reader.close();

                clientSocket.close();

            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }

        private void login(String line, User user) {

            try {

                Response<Login> response = new Response<>();

                Request<Login> r = mapper.readValue(line, new TypeReference<Request<Login>>() {});

                Login login = r.getPayload();
                User u = userDAO.get(login.getEmail());

                if(u == null) {

                    user.setLogin(login.getEmail());
                    user.setPassword(Password.hash(login.getPassword()));

                    userDAO.save(user);
                    user.setUser_id(userDAO.get(login.getEmail()).getUser_id());

                    response.setResponseMessage("You'r registered");
                    u = user;
                }

                else if (u.getLogin().equals(login.getEmail())) {

                    if(!Password.check(login.getPassword(), u.getPassword())) {
                        response.setResponseMessage("Wrong password");
                        u = null;
                    }

                    else {
                        response.setResponseMessage("You'r log in");
                    }
                }

                else {
                    System.out.println("WTF");
                }

                String token = u == null ? null : TokenUtil.createToken(u.getUser_id(), true);
                r.setToken(token);

                JSON<Response<Login>> json = new JSON<>();
                response.setToken(token);
                Login l = new Login();

                if(u != null) {
                    l.setEmail(u.getLogin());
                    l.setUserId(u.getUser_id());
                }

                response.setResponse(l);
                response.setHeader("login");

                writer.println(json.createJSON(response));

            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

        }

        private String userMessage(String line, User user) {

            try {

                Request<Message> r = mapper.readValue(line, new TypeReference<Request<Message>>() {});

                if(r.getToken() != null) {
                    Message m = r.getPayload();

                    Date date = new Date(new java.util.Date().getTime());

                    Message message = new Message(user.getUser_id(), m.getText(), date);

                    new MessageDAO().save(message);

                    Response<Message> response = new Response<>();
                    response.setHeader("message");
                    response.setResponse(message);
                    response.setToken(r.getToken());

                    return new JSON<Response>().createJSON(response);
                }

            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

            return "text from not logined user";

        }

        private String addProduct(String line) {

            try {

                Request<AddProduct> r = mapper.readValue(line, new TypeReference<Request<AddProduct>>() {});

                ProductDAO productDAO = new ProductDAO();
                productDAO.save(new Product(24, r.getPayload().getName(), r.getPayload().getPrice()));

                Response<AddProduct> response = new Response<>();
                response.setHeader("add-product");
                response.setToken(r.getToken());
                response.setResponseMessage("product added");

                return new JSON<Response<AddProduct>>().createJSON(response);

            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

            System.out.println("add product");

            return "add product";

        }

        private String deleteProduct(String line) {

            try {

                Request<DeleteProduct> r = mapper.readValue(line, new TypeReference<Request<DeleteProduct>>() {});

                Response<DeleteProduct> response = new Response<>();
                response.setToken(r.getToken());
                response.setResponseMessage("product deleted");
                ProductDAO productDAO = new ProductDAO();
                Product product = new Product();
                product.setId(r.getPayload().getId());
                productDAO.save(product);

            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

            System.out.println("delete product");

            return "add product";
        }

        private String listOfProducts(String line) {

            Response<PaginationResponse<Product>> response = new Response<>();
            response.setHeader("product-list");

            try {

                Request<PaginationRequest> r = mapper.readValue(line, new TypeReference<Request<PaginationRequest>>() {});

                PaginationRequest pg = r.getPayload();

                ProductDAO productDAO = new ProductDAO();

                PaginationResponse<Product> pr = new PaginationResponse<>();

                pr.setData(productDAO.pagination(pg));

                response.setResponse(pr);

                writer.println(new JSON<Response>().createJSON(response));

                System.out.println("get-list");

            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

            JSON<Response<PaginationResponse<Product>>> responseJSON = new JSON<>();

            return responseJSON.createJSON(response);

        }

    }
}