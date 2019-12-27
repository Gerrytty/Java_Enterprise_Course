package server;

public class ServerMain {
    public static void main(String[] args) {

        MultiClientServer multiClientServer = new MultiClientServer();
        multiClientServer.start(7000);

    }
}
