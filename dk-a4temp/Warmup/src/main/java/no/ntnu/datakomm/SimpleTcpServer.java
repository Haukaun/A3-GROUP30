package no.ntnu.datakomm;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * A Simple TCP server, used as a warm-up exercise for assignment A4.
 */
public class SimpleTcpServer {
    public static void main(String[] args) {
        SimpleTcpServer server = new SimpleTcpServer();
        log("Simple TCP server starting");
        server.run();
        log("ERROR: the server should never go out of the run() method! After handling one client");
    }

    public void run() {
        // TODO - implement the logic of the server, according to the protocol.
        // Take a look at the tutorial to understand the basic blocks: creating a listening socket,
        // accepting the next client connection, sending and receiving messages and closing the connection

        try {
            ServerSocket welcomeSocket = new ServerSocket(5555);

            boolean mustRun = true;
            while(mustRun) {
                Socket clientSocket = welcomeSocket.accept();
                System.out.println("new client connected");
                welcomeSocket.getInetAddress();

                PrintWriter outToClient = new PrintWriter(clientSocket.getOutputStream(), true);
                outToClient.println("HELLO");

                try {
                    Thread.sleep(10 * 1000);

                } catch (InterruptedException e){
                    System.out.println("sleep interrupted");
                }
                System.out.println("closing client socket...");
                clientSocket.close();
            }

            System.out.println("server shutting down.");
            welcomeSocket.close();

        }catch (IOException e){
            System.out.println("Could not open a listening socket: " + e.getMessage());
        }
    }

    /**
     * Log a message to the system console.
     *
     * @param message The message to be logged (printed).
     */
    private static void log(String message) {
        System.out.println(message);
    }
}
