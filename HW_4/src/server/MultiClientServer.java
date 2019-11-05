package server;

import ORM.Message;
import ORM.User;

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

                String loginAndPassword = reader.readLine();
                loginAndPassword(loginAndPassword);
                int id = User.getUser_id(loginAndPassword.split(" ")[0]);

                String line;
                while ((line = reader.readLine()) != null) {
                    Date date = new Date(new java.util.Date().getTime());
                    addMessageToDB(id, line, date);
                    System.out.println(line);
                    for (ClientHandler client : clients) {
                        PrintWriter writer = new PrintWriter(
                                client.clientSocket.getOutputStream(), true);
                        writer.println(line);
                    }
                }
                reader.close();
                clientSocket.close();
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }

        private void loginAndPassword(String s) {
            addToDataBase(s.split(" ")[0], s.split(" ")[1]);
        }

        private void addToDataBase(String login, String password) {
            System.out.println(login + " " + password);
            User user = new User(login, password);
            user.addToDB();
        }

        private void addMessageToDB(int user_id, String text, Date date) {
            Message message = new Message(user_id, text, date);
            message.addToDB();
        }

    }
}