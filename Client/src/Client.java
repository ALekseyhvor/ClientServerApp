import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        Client client = new Client();
        client.go();
    }
    public void go() {
        try (Socket clientSocket = new Socket("127.0.0.1", 7000)){
            System.out.println("Connected to server");
            BufferedWriter writer =
                     new BufferedWriter(
                             new OutputStreamWriter(
                                     clientSocket.getOutputStream()));
             BufferedReader reader =
                     new BufferedReader(
                             new InputStreamReader(
                                     clientSocket.getInputStream()));

            String responseWelcome = reader.readLine();
            System.out.println("Response: " + responseWelcome);

            String line = "";
             DataInputStream input = new DataInputStream(System.in);
             while(!line.equals("exit"))
             {
                 try {
                     line = input.readLine();
                     writer.write(line);
                     System.out.println("You writing: " + line);
                     writer.newLine();
                     writer.flush();
                 }catch (IOException e){
                     e.printStackTrace();
                 }
             }

            String response = reader.readLine();
            System.out.println("Response: " + response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
