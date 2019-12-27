package client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Message;
import model.Product;
import protocol.*;
import utils.JSON;
import utils.TokenUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

public class ChatClient {

    private Socket clientSocket;
    private PrintWriter writer;
    private BufferedReader reader;

    private String token = null;
    private int userId = 0;
    private String name = "";

    private String commandName = "";

    ObjectMapper mapper = new ObjectMapper();

    public void startConnection(String ip, int port) {

        try {

            clientSocket = new Socket(ip, port);
            writer = new PrintWriter(clientSocket.getOutputStream(), true);
            reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            new Thread(receiveMessagesTask).start();

        }
        catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public void sendMessage(String message) {

        writer.println(message);
    }

    private Runnable receiveMessagesTask = new Runnable() {

        Scanner sc = new Scanner(System.in);

        public void run() {

            while (true) {

                String message = sc.nextLine();

                String messageToServer;
                messageToServer = message;

                if(message == null) {
                    System.exit(0);
                }

                else if(message.equals("/login")) {
                    if(token == null) {
                        messageToServer = login(sc);
                    }
                    else {
                        System.out.println("You're already auth");
                    }
                }

                else if(message.equals("/logout")) {

                    if(token == null) {
                        System.out.println("You can't logout because you don't auth");
                    }
                    else {
                        System.out.println("You have successfully logged out");
                        token = null;
                    }

                }

                else if(message.startsWith("/add-product")) {

                    if(token == null || !TokenUtil.verifyToken(token, true)) {
                        System.out.println("You can't do this command because you don't auth or you don't admin, please login with command /login");
                    }
                    else {
                        messageToServer = addProduct(sc);
                    }

                }

                else if(message.startsWith("/delete-product")) {

                    if(token == null || !TokenUtil.verifyToken(token, true)) {
                        System.out.println("You can't do this command because you don't auth or you don't admin, please login with command /login");
                    }
                    else {
                        messageToServer = deleteProduct(sc);
                    }
                }

                else if(message.startsWith("/get-list")) {
                    messageToServer = getListRequest(sc);
                }

                else {
                    if(token == null) {
                        System.out.println("You can't write while you don't auth");
                        continue;
                    }
                    else {
                        messageToServer = message(message);
                    }
                }

                System.out.println("Message to server = " + messageToServer);
                sendMessage(messageToServer);

                try {

                    String messageFromServer = reader.readLine();
                    System.out.println("Message from server = " + messageFromServer);

                    if(commandName.equals("product-list")) {
                        productList(messageFromServer);
                    }

                    else if(commandName.equals("login")) {
                        loginResponse(messageFromServer);
                    }

                    else if(commandName.equals("message")) {
                        Response<Message> r = mapper.readValue(messageFromServer, new TypeReference<Response<Message>>() {});
                        System.out.println(r.getData().getText());
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    };

    private void productList(String messageFromServer) {

        try {

            Response<List<Product>> r = mapper.readValue(messageFromServer, new TypeReference<Response<List<Product>>>() {});
            List<Product> list = r.getData();
            System.out.println("List of products:");
            list.forEach(product -> System.out.println(product.getName() + " price = " + product.getPrice()));

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }

    private String addProduct(Scanner scanner) {

        System.out.println("Enter product name: ");
        String name = scanner.nextLine();
        System.out.println("Enter price: ");
        int price = scanner.nextInt();
        System.out.println("Product added");

        JSON<Request<AddProduct>> json = new JSON<>();
        AddProduct addProduct = new AddProduct();
        addProduct.setName(name);
        addProduct.setPrice(price);
        addProduct.setUserId(userId);
        Request<AddProduct> request = new Request<>("add-product", addProduct, token);

        return json.createJSON(request);

      }

    private String deleteProduct(Scanner scanner) {

        System.out.println("Enter product id: ");
        int id = scanner.nextInt();
        System.out.println("Product deleted");

        JSON<Request<DeleteProduct>> json = new JSON<>();
        DeleteProduct deleteProduct = new DeleteProduct();
        deleteProduct.setId(id);
        Request<DeleteProduct> request = new Request<>("delete-product", deleteProduct, token);

        return json.createJSON(request);

    }

    private String login(Scanner scanner) {

        System.out.println("Enter your login: ");
        String login = scanner.nextLine();
        System.out.println("Enter your password: ");
        String password = scanner.nextLine();

        return new JSON<Request<Login>>().createJSON(new Request<>("login",
                new Login(login, password), token));

    }

    private String message(String message) {

        Request<Message> request = new Request<>();
        request.setHeader("message");
        request.setToken(token);
        Message message1 = new Message();
        message1.setText(message);
        request.setPayload(message1);

        return new JSON<Request<Message>>().createJSON(request);

    }

    private void loginResponse(String message) {

        try {

            Response<Login> r = mapper.readValue(message, new TypeReference<Response<Login>>() {});
            if(r.getData().getEmail() == null) {
                System.out.println("login failed");
            }
            else {
                token = TokenUtil.createToken(r.getData().getUserId(), r.getData().getRole() == 1);
                System.out.println("successfully");
            }

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }

    public String getListRequest(Scanner scanner) {
        Pagination pagination = new Pagination();

        if(pagination.getStart() == 0 && pagination.getEnd() == 0) {

            System.out.print("Enter start: ");
            pagination.setStart(scanner.nextInt());
            System.out.print("Enter end: ");
            pagination.setEnd(scanner.nextInt());

        }

        return new JSON<Request<Pagination>>().createJSON(new Request<>("command", pagination));
    }

}








