package server;

import DAO.*;
import ORM.Message;
import ORM.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import commands.*;
import utils.JSON;

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

    public MultiClientServer() {
        clients = new ArrayList<ClientHandler>();
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
            ObjectMapper mapper = new ObjectMapper();

            System.out.println("in run");

            try {

                reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                writer = new PrintWriter(clientSocket.getOutputStream(), true);

                String line;

                User user = new User();
                UserDAO userDAO = new UserDAO();

                while ((line = reader.readLine()) != null) {

                    String header = mapper.readValue(line, Request.class).getHeader();

                    if(header.equals("login")) {

                        Request<Login> request = mapper.readValue(line, new TypeReference<Request<Login>>() {});

                        Login login = request.getPayload();

                        user = new User(login.getEmail(), login.getPassword());
                        userDAO.save(user);
                        user.setUser_id(userDAO.getUser_id(user));

                    }

                    else if(header.equals("command")) {

                        Request<PaginationRequest> request = mapper.readValue(line,
                                new TypeReference<Request<PaginationRequest>>() {});

                        PaginationRequest pg = request.getPayload();

                        MessageDAO messageDAO = new MessageDAO();

                        PaginationResponse pr = new PaginationResponse();

                        pr.setData(messageDAO.pagination(pg));

                        writer.println(new JSON<PaginationResponse>().createJSON(pr));
                    }

                    else if(header.equals("logout")) {
                        userDAO.delete(user);
                    }

                    else if(header.equals("message")) {

                        Request<commands.Message> request = mapper.readValue(line, new TypeReference<Request<commands.Message>>() {});

                        commands.Message m = request.getPayload();

                        Date date = new Date(new java.util.Date().getTime());

                        Message message = new Message(user.getUser_id(), m.getText(), date);

                        new MessageDAO().save(message);

                    }

                    for (ClientHandler client : clients) {

                        PrintWriter writer = new PrintWriter(client.clientSocket.getOutputStream(), true);

                        writer.println(line);
                    }
                }

                reader.close();

                clientSocket.close();

            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }
    }
}