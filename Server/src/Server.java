import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        Server server = new Server();
        server.go();

    }
    public void go(){

        try ( ServerSocket serverSocket = new ServerSocket(7000))
        {
            System.out.println("Server started");
            while (true)
                try {
                    Socket socket = serverSocket.accept();
                        new Thread(() -> {
                            try {


                                BufferedWriter writer =
                                        new BufferedWriter(
                                                new OutputStreamWriter(
                                                        socket.getOutputStream()));
                                BufferedReader reader =
                                        new BufferedReader(
                                                new InputStreamReader(
                                                        socket.getInputStream()));

                                String request = reader.readLine();
                                System.out.println("Request: " + request);

                                String response = "Hi, client";
                                System.out.println("Response: " + response);

                                try {Thread.sleep(3000);} catch (InterruptedException e) { e.printStackTrace(); }
                                writer.write(response);
                                writer.newLine();
                                writer.flush();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }).start();

                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
