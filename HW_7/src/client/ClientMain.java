package client;

import java.util.Scanner;

public class ClientMain {
    private static String ip = "127.0.0.1";
    private static int port = 7000;

    public static void main(String[] args) {

        setPortAndIP(args);

        ChatClient chatClient = new ChatClient();
        chatClient.startConnection("127.0.0.1", 7000);

        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to the app!");
        System.out.println("Enter /login to login");
        System.out.println("Enter /logout to logout (should be auth)");
        System.out.println("Enter /get-list to get list of products (can be don't auth)");
        System.out.println("Enter /add-product to add product (should be auth and admin)");
        System.out.println("Enter /delete-product to delete-product (should be auth and admin)");

//        while (true) {
//
//            String message = sc.nextLine();
//            chatClient.sendMessage(message);
//
//        }

    }

    private static void setPortAndIP(String[] args) {

        if(args.length >= 2) {

            ip = args[0].substring(args[0].lastIndexOf("=") + 1);
            port = Integer.parseInt(args[1].substring(args[1].lastIndexOf("=") + 1));

        }
    }
}