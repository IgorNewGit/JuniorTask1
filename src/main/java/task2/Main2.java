package task2;

import java.io.IOException;
import java.net.ServerSocket;

public class Main2 {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket= new ServerSocket(8080);
        while (true) {
            new TcpConnection(serverSocket.accept());
            System.out.println("Client accepted");
        }
    }
}
