package server;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.security.ntlm.Client;
import context.ApplicationContext;
import context.ApplicationContextReflectionBased;
import dispatcher.RequestsDispatcher;
import dto.ProductDto;
import dto.UserDto;
import model.Product;
import protocol.*;
import repositories.product.ProductsRepository;
import repositories.product.ProductsRepositoryImpl;
import services.message.AddMessageService;
import services.message.AddMessageServiceImpl;
import services.product.AddProductServiceImpl;
import services.product.DeleteProductServiceImpl;
import services.product.ListOfProductsService;
import services.product.ListOfProductsServiceImpl;
import services.shoppingСart.AddProductToCartServiceImpl;
import services.shoppingСart.AddProductToShoppingCartService;
import services.shoppingСart.ListOfProductsInCartService;
import services.shoppingСart.ListOfProductsInCartServiceImpl;
import utils.JSON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class MultiClientServer {

    private ServerSocket serverSocket;
    private List<ClientHandler> clients;

    private ObjectMapper mapper;
    private ApplicationContext applicationContext;
    private RequestsDispatcher requestsDispatcher;

    public MultiClientServer() {
        clients = new ArrayList<>();
        mapper = new ObjectMapper();
        applicationContext = new ApplicationContextReflectionBased();
        requestsDispatcher = new RequestsDispatcher();
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

                String messageFromClient;

                while ((messageFromClient = reader.readLine()) != null) {

                    String messageToClient = "";

                    if(messageFromClient.equals("hello")) {
                        messageToClient = "Hi from serer!";
                        writer.println(messageToClient);
                    }

                    else {
                        Request request = mapper.readValue(messageFromClient, Request.class);
                        String header = request.getHeader();

                        if(header.equals("command")) {
                            messageToClient = getListResponse(messageFromClient);
                            writer.println(messageToClient);
                        }

                        else if(header.equals("login")) {
                            messageToClient = login(messageFromClient);
                            writer.println(messageToClient);
                        }

                        else if(header.equals("message")) {
                            addMessage(messageFromClient);
                        }

                        else if(header.equals("add-product")) {
                            addProduct(messageFromClient);
                        }

                        else if (header.equals("delete-product")) {
                           deleteProduct(messageFromClient);
                        }

                        else if(header.equals("logout")) {
                            clients.remove(this);
                        }

                    }

                }

                reader.close();

                clientSocket.close();

            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }


        private String login(String line) {

            Response<List<ProductDto>> response = new Response<>();

            try {

                Request<Login> loginRequest = mapper.readValue(line, new TypeReference<Request<Login>>() {});

                UserDto userDto = (UserDto) requestsDispatcher.doDispatch(loginRequest);

                if (userDto != null) {
                    clients.add(this);
                    return new JSON<Response<String>>().createJSON(Response.build(userDto.getToken()));
                }

            }
            catch (JsonProcessingException e) {
                e.printStackTrace();
            }

            return new JSON<>().createJSON(Response.build(null));


        }

        private String getListResponse(String line) {

            Response<List<ProductDto>> response = new Response<>();

            try {

                Request<Pagination> r = mapper.readValue(line, new TypeReference<Request<Pagination>>() {});
                Pagination pagination = r.getPayload();

                List<ProductDto> list = applicationContext.getComponent(ListOfProductsServiceImpl.class, "listOfProductsService")
                        .find(pagination.getStart(), pagination.getEnd());

                response.setData(list);

                writer.println(new JSON<Response>().createJSON(response));

                System.out.println("get-list");

            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

            JSON<Response<List<ProductDto>>> responseJSON = new JSON<>();

            return responseJSON.createJSON(response);

        }

        private void addProduct(String line) {

            try {

                Request<AddProduct> r = mapper.readValue(line, new TypeReference<Request<AddProduct>>() {});
                AddProduct addProduct = r.getPayload();

                applicationContext.getComponent(AddProductServiceImpl.class, "addProductService")
                        .addProduct(addProduct.getName(), addProduct.getPrice());

                System.out.println("add product");

            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

        }

        private void deleteProduct(String line) {

            try {

                Request<DeleteProduct> deleteProductRequest = mapper.readValue(line, new TypeReference<Request<DeleteProduct>>() {});
                applicationContext.getComponent(DeleteProductServiceImpl.class, "deleteProductService")
                        .deleteProduct(deleteProductRequest.getPayload().getId());

                System.out.println("delete product");
            }
            catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }

        private void addMessage(String line) {

            try {

                Request<AddMessage> message = mapper.readValue(line, new TypeReference<Request<AddMessage>>() {});
                AddMessage addMessage = message.getPayload();

                for (ClientHandler client : clients) {

                    PrintWriter writer = new PrintWriter(client.clientSocket.getOutputStream(), true);

                    writer.println(addMessage.getName() + ": " + addMessage.getText());
                }

                applicationContext.getComponent(AddMessageServiceImpl.class, "addMessageService")
                        .save(addMessage.getText(), addMessage.getId());

            }
            catch (JsonProcessingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }

    }
}