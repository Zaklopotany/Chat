package thread;

import com.sun.corba.se.impl.io.OutputStreamHook;
import com.sun.corba.se.impl.ior.WireObjectKeyTemplate;
import user.ChatClient;
import user.TextDemo;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by mzukowski on 09/04/2018.
 */
public class WriteThread extends Thread {
    private PrintWriter writer;
    private Socket socket;
    private ChatClient client;
    private TextDemo textDemo;

    public WriteThread(Socket socket, ChatClient client) {
        this.socket = socket;
        this.client = client;
        this.textDemo = TextDemo.getTextDemo();

        try {
            OutputStream output = socket.getOutputStream();
            writer = new PrintWriter(output, true);


        } catch (IOException e) {
            System.out.println("Error getting output stream: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            textDemo.addToArea("\nEnter your name: ");

      System.out.println("\nEnter your name: ");
        String userName = reader.readLine();
        client.setUserName(userName);
        writer.println(userName);

        String text;

            do {
                text = reader.readLine();
                writer.println(text);
            } while (!text.equals("bye"));

            try {
                socket.close();
            } catch (IOException e) {
                System.out.println("System writing to server: " + e.getMessage());
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
