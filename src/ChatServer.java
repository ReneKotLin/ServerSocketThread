import IOException.IOException;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ChatServer {
    ArrayList<Client> clients = new ArrayList<>();
    ServerSocket serverSocket;

    ChatServer() throws IOException, java.io.IOException {
        serverSocket = new ServerSocket(1234);
        // создаем серверный сокет на порту 1234
    }
    void sendAll(String message) {
        for (Client client : clients) {
            client.receive(message);
        }

    }

    public void run() {
        while (true) {
            System.out.println("Waiting...");

            try {
                // wait client
                Socket socket = serverSocket.accept();
                System.out.println("Client connected!");
                // создаем клиента на своей стороне
                clients.add(new Client(socket, this));

            } catch (java.io.IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public static void main(String[] args) throws IOException, java.io.IOException {
        new ChatServer().run();
    }
}