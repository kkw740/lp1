import java.io.*;
import java.net.*;
import java.util.concurrent.atomic.AtomicReference;

public class SimpleServer {
    public static void main(String[] args) throws IOException {
        // To store the latest computed result (shared between threads)
        AtomicReference<String> latestResult = new AtomicReference<>("No result yet");

        // --- THREAD 1: TCP Socket Server (for clients) ---
        new Thread(() -> {
            try (ServerSocket serverSocket = new ServerSocket(5000)) {
                System.out.println("Socket server started on port 5000");

                while (true) {
                    Socket socket = serverSocket.accept();
                    System.out.println("Client connected: " + socket.getInetAddress());

                    BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

                    String clientMessage;
                    while ((clientMessage = input.readLine()) != null) {
                        System.out.println("Received: " + clientMessage);
                        String[] parts = clientMessage.trim().split("\\s+");
                        if (parts.length == 2) {
                            try {
                                int a = Integer.parseInt(parts[0]);
                                int b = Integer.parseInt(parts[1]);
                                int sum = a + b;
                                String result = "Sum is: " + sum;
                                output.println(result);
                                latestResult.set(result); // update shared value for browser
                            } catch (NumberFormatException e) {
                                output.println("Invalid input. Please send two integers.");
                            }
                        } else {
                            output.println("Please send two numbers separated by space.");
                        }
                    }

                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        // --- THREAD 2: HTTP Server (for browser) ---
        new Thread(() -> {
            try (ServerSocket httpSocket = new ServerSocket(8080)) {
                System.out.println("HTTP server started on port 8080");
                while (true) {
                    Socket client = httpSocket.accept();
                    BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                    OutputStream out = client.getOutputStream();

                    // Read and ignore HTTP request headers
                    while (true) {
                        String line = in.readLine();
                        if (line == null || line.isEmpty()) break;
                    }

                    // Create simple HTML response
                    String response = "<html><body style='font-family:Arial;text-align:center;margin-top:50px;'>"
                            + "<h1>Latest Result:</h1>"
                            + "<h2>" + latestResult.get() + "</h2>"
                            + "<p>Refresh this page to see updates.</p>"
                            + "</body></html>";

                    String header = "HTTP/1.1 200 OK\r\n" +
                            "Content-Type: text/html\r\n" +
                            "Content-Length: " + response.length() + "\r\n\r\n";

                    out.write((header + response).getBytes());
                    out.flush();
                    client.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}

