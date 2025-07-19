# Java Multithreaded Chat Application

A **multithreaded chat application** built in Java that allows multiple clients to connect to a single server and exchange real-time messages via the console.

This project demonstrates the use of Java Sockets, threading, and basic networking to build a lightweight messaging system.

---

## 🔧 Features

- Multi-client support using threads
- Real-time message broadcasting
- Easy-to-use console interface
- `/quit` command for clean exit
- Written in pure Java (no external libraries)

---

## 📁 Project Structure

java-chat-app/
├── ChatServer.java # Multithreaded server code
├── ChatClient.java # Console-based client code
└── README.md # Project overview and instructions

yaml
----
## 🚀 Getting Started

### 🛠️ Prerequisites

- Java Development Kit (JDK) 8 or higher
- Terminal or command prompt

---

### 🖥️ How to Compile:
javac ChatServer.java ChatClient.java


🔌 Run the Server:
java ChatServer 5050
Replace 5050 with any available port.

💬 Run the Clients (in separate terminals):
java ChatClient localhost 5050 Alice
java ChatClient localhost 5050 Bob
localhost → IP address of server machine (or keep as localhost if on the same system)

Alice, Bob → Names of users

❓ Commands
Command	Description
/quit	Leave the chat session

📷 Sample Output
Server Terminal:
🟢 Chat Server started on port 5050
🔗 New client connected: /127.0.0.1
🟡 Alice joined the chat.
[Alice]: Hello everyone!

Client Terminal (Alice):
✅ Connected to the server
🟡 Alice joined the chat.
[Alice]: Hello everyone!
📚 Topics Covered
Java Networking (Sockets, ServerSocket)

Multithreading

Console I/O

Real-time communication

📄 License
This project is open-source and available under the MIT License.

✨ Author
Yabez Yalsatty


