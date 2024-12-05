package server;

import utils.Printer;
import utils.Reader;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static int port = 4040;

    public static void main(String[] args) {
        Printer.println("Server started");
        try (ServerSocket server = new ServerSocket(port)) {
            while (true) {
                Socket socket = server.accept();
                Printer.println("Found new client");

                InputStream in = socket.getInputStream();
                OutputStream out = socket.getOutputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                PrintWriter writer = new PrintWriter(out, true);
                writer.println("Hello my new guest");
                while (true) {
                    String message = reader.readLine();
                    Printer.println("Client message -> " + message);
                    if (message == null) {
                        Printer.println("socket closed");
                        socket.close();
                    }
                    Reader lineReader = new Reader();
                    String send = lineReader.nextLine();
                    writer.println("Server response -> " + send);
                }

            }
        } catch (Exception e) {
            Printer.println("Found exception " + e.getMessage());
        }
    }
}
