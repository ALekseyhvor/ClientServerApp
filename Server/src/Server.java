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
                    System.out.println("Client connected");
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

                                String welcome = "Enter your name: ";
                                writer.write(welcome);
                                writer.newLine();
                                writer.flush();

                                String request = "";
                                while(!request.equals("exit"))
                                {
                                    try {
                                        request = reader.readLine();
                                        System.out.println(request);
                                    }catch (IOException e){
                                        e.printStackTrace();
                                    }
                                }
                                System.out.println("Client disconnected");
                                String response = "You disconnected from server";
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
