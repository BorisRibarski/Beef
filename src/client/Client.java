package client;

import utils.Printer;
import utils.Reader;

import java.io.*;
import java.net.Socket;

public class Client {
    private static String hostname = "localhost";
    private static int port = 4040;

    public static void main(String[] args) {
        Printer.println("Client started");
        try (Socket socket = new Socket(hostname, port)) {
            InputStream in = socket.getInputStream();
            OutputStream out = socket.getOutputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            PrintWriter writer = new PrintWriter(out, true);
            while(true){
                Printer.println(reader.readLine());
                Reader lineReader = new Reader();
                String line = lineReader.nextLine();
                if(line.equals("exit"))break;
                writer.println(line);
            }
            socket.close();
        } catch (Exception e) {
            Printer.println("Found exception " + e.getMessage());
        }
    }
}
