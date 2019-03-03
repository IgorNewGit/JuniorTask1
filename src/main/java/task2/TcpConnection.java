package task2;

import java.io.*;
import java.net.Socket;

public class TcpConnection {

    private final Socket socket;
    private final Thread rxThread;
    private final BufferedReader in;
    private final BufferedWriter out;

    public TcpConnection(Socket socket) throws IOException {
        this.socket = socket;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        rxThread = new Thread(new Runnable() {
            public void run(){
                while(true) {
                    String inputString = null;
                    try {
                        inputString = in.readLine();
                        if (inputString == null || inputString.trim().length() == 0) {
                            break;
                        }
                        writeResponse(inputString);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        rxThread.start();
    }

    private void writeResponse(String inputString) throws IOException {
        String response = "HTTP/1.1 200 OK\r\n" +
                "Content-Type: text/html\r\n" +
                "Connection: close\r\n\r\n";
        String result = response + inputString;
        out.write(result);
        out.flush();
    }
}
