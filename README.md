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

---

## 🚀 Getting Started

### 🛠️ Prerequisites

- Java Development Kit (JDK) 8 or higher
- Terminal or command prompt

---

### 🖥️ How to Compile

```bash
javac ChatServer.java ChatClient.java


 Run the Server
bash
Copy
Edit
java ChatServer 5050


Run the Clients (in separate terminals)
bash
Copy
Edit
java ChatClient localhost 5050 Alice
java ChatClient localhost 5050 Bob
