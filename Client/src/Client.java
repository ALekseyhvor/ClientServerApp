import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        Client client = new Client();
        client.go();
    }
    public void go() {
        try (Socket clientSocket = new Socket("127.0.0.1", 7000)){
             BufferedWriter writer =
                     new BufferedWriter(
                             new OutputStreamWriter(
                                     clientSocket.getOutputStream()));
             BufferedReader reader =
                     new BufferedReader(
                             new InputStreamReader(
                                     clientSocket.getInputStream()));

            System.out.println("Connected to server");
            String request = "Hi, server";
            writer.write(request);
            writer.newLine();
            writer.flush();

            String response = reader.readLine();
            System.out.println("Response: " + response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
