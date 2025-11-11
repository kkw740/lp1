import java.io.*;
import java.net.*;

public class SimpleClient {
    public static void main(String[] args) throws IOException {
        // Connect to the server — use "localhost" if on same system
        Socket socket = new Socket("localhost", 5000);
        System.out.println("Connected to server.");

        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

        String serverMessage;
        while (true) {
            System.out.print("Enter two numbers (e.g., 5 7): ");
            String message = console.readLine();
            output.println(message);

            serverMessage = input.readLine();
            System.out.println("Server replies: " + serverMessage);
        }
    }
}

