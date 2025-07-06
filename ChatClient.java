/**
 * ChatClient.java
 * 
 * A console-based client application that connects to a ChatServer
 * to send and receive real-time messages.
 *
 * Usage:
 *   Compile: javac ChatClient.java
 *   Run:     java ChatClient <host> <port> <name>
 *
 * Example:
 *   java ChatClient localhost 5050 Alice
 */

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ChatClient {

    /**
     * Main method that connects to the server and starts input/output threads.
     *
     * @param args Command line arguments (host, port, username)
     */
    public static void main(String[] args) throws IOException {
        if (args.length != 3) {
            System.out.println("Usage: java ChatClient <host> <port> <name>");
            return;
        }

        String host = args[0];
        int port = Integer.parseInt(args[1]);
        String name = args[2];

        try {
            // Connect to the server
            Socket socket = new Socket(host, port);
            System.out.println("✅ Connected to the server");

            // Send messages to server
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // Read messages from server
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Send user name to server
            out.println(name);

            // Thread to read server messages
            Thread readerThread = new Thread(() -> {
                String serverMessage;
                try {
                    while ((serverMessage = in.readLine()) != null) {
                        System.out.println(serverMessage);
                    }
                } catch (IOException e) {
                    System.out.println("❌ Connection closed.");
                }
            });
            readerThread.start();

            // Read user input and send to server
            Scanner scanner = new Scanner(System.in);
            while (true) {
                String userMessage = scanner.nextLine();
                out.println(userMessage);
                if (userMessage.equalsIgnoreCase("/quit")) {
                    break;
                }
            }

            socket.close();
        } catch (IOException e) {
            System.out.println("❌ Could not connect to server: " + e.getMessage());
        }
    }
}
