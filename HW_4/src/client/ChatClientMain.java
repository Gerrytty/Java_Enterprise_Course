package client;

import java.util.Scanner;

public class ChatClientMain {
    public static void main(String[] args) {

//        String ip = args[0].substring(args[0].lastIndexOf("=") + 1);
//        int port = Integer.parseInt(args[1].substring(args[1].lastIndexOf("=") + 1));

        String ip = "127.0.0.1";
        int port = 7000;
        ChatClient chatClient = new ChatClient();
        chatClient.startConnection(ip, port);
        Scanner sc = new Scanner(System.in);
        System.out.print("Your login: ");
        String login = sc.nextLine();
        System.out.print("Your password: ");
        String password = sc.nextLine();
        chatClient.setLoginAndPassword(login, password);

        while (true) {
            String message = sc.nextLine();
            chatClient.sendMessage(message);
        }
    }
}