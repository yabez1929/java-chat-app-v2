/**
 * ChatServer.java
 * 
 * A multithreaded chat server application that allows multiple clients
 * to connect simultaneously and exchange messages in real time.
 *
 * Each client is handled using a separate thread (ClientHandler).
 * Messages received from a client are broadcast to all other clients.
 *
 * Usage:
 *   Compile: javac ChatServer.java
 *   Run:     java ChatServer <port>
 *
 * Example:
 *   java ChatServer 5050
 */

import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {

    // Thread-safe set of all connected clients
    private static final Set<ClientHandler> clients = Collections.synchronizedSet(new HashSet<>());

    /**
     * Main method that starts the server on a given port.
     * 
     * @param args Command line arguments (expects a single port number)
     */
    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.out.println("Usage: java ChatServer <port>");
            return;
        }

        int port = Integer.parseInt(args[0]);
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("🟢 Chat Server started on port " + port);

        // Infinite loop to accept incoming client connections
        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("🔗 New client connected: " + socket);

            ClientHandler clientHandler = new ClientHandler(socket);
            clients.add(clientHandler);
            clientHandler.start();  // Start client thread
        }
    }

    /**
     * Broadcasts a message to all connected clients except the sender.
     *
     * @param message The message to broadcast
     * @param excludeUser The client to exclude from receiving the message
     */
    public static void broadcast(String message, ClientHandler excludeUser) {
        synchronized (clients) {
            for (ClientHandler client : clients) {
                if (client != excludeUser) {
                    client.sendMessage(message);
                }
            }
        }
    }

    /**
     * Removes a client from the connected client set.
     *
     * @param client The client to remove
     */
    public static void removeClient(ClientHandler client) {
        clients.remove(client);
    }

    /**
     * Inner class that handles communication with a single client.
     */
    static class ClientHandler extends Thread {
        private Socket socket;
        private PrintWriter out;
        private BufferedReader in;
        private String name;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        /**
         * Sends a message to this client.
         *
         * @param message The message to send
         */
        public void sendMessage(String message) {
            out.println(message);
        }

        /**
         * The run method that handles client communication in a thread.
         */
        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);

                // Ask the client for a display name
                out.println("Enter your name: ");
                name = in.readLine();
                if (name == null || name.trim().isEmpty()) {
                    name = "Anonymous";
                }

                broadcast("🟡 " + name + " joined the chat.", this);

                // Read and broadcast messages from client
                String message;
                while ((message = in.readLine()) != null) {
                    if (message.equalsIgnoreCase("/quit")) {
                        break;
                    }
                    broadcast("[" + name + "]: " + message, this);
                }
            } catch (IOException e) {
                System.out.println("❌ Error: " + e.getMessage());
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    System.out.println("❌ Could not close socket.");
                }
                removeClient(this);
                broadcast("🔴 " + name + " left the chat.", this);
            }
        }
    }
}

