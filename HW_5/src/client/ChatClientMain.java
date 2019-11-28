package client;

import commands.*;
import utils.JSON;
import utils.Password;

import java.util.Scanner;

public class ChatClientMain {

    private static String ip = "127.0.0.1";
    private static int port = 7000;

    public static void main(String[] args) {

        setPortAndIP(args);

        ChatClient chatClient = new ChatClient();
        chatClient.startConnection(ip, port);
        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome!");
        System.out.print("Enter your login: ");
        String login = sc.nextLine();
        System.out.print("Enter your password: ");
        String password = sc.nextLine();

        chatClient.sendMessage(new JSON<Request<Login>>().createJSON(new Request<>("login",
                new Login(login, password))));


        while (true) {

            String message = sc.nextLine();

            if(message.equals("/logout")) {

                chatClient.sendMessage(new JSON<Request<Logout>>().createJSON(new Request<>("logout",
                                new Logout())));

                System.exit(0);
            }

            else if(message.startsWith("/get-messages")) {

                message = new JSON<Request<PaginationRequest>>().createJSON(new Request<>("command",
                        new PaginationRequest(sc.nextInt(), sc.nextInt())));

            }

            else {

                message = new JSON<Request<Message>>().createJSON(new Request<>("message",
                        new commands.Message(message)));

            }

            chatClient.sendMessage(message);
        }
    }

    private static void setPortAndIP(String[] args) {

        if(args.length >= 2) {

            ip = args[0].substring(args[0].lastIndexOf("=") + 1);
            port = Integer.parseInt(args[1].substring(args[1].lastIndexOf("=") + 1));

        }
    }
}