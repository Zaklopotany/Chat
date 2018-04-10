package thread;

import com.sun.corba.se.impl.io.OutputStreamHook;
import com.sun.corba.se.impl.ior.WireObjectKeyTemplate;
import user.ChatClient;

import java.io.Console;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by mzukowski on 09/04/2018.
 */
public class WriteThread extends Thread {
    private PrintWriter writer;
    private Socket socket;
    private ChatClient client;

    public WriteThread(Socket socket, ChatClient client) {
        this.socket = socket;
        this.client = client;

        try {
            OutputStream output = socket.getOutputStream();
            writer = new PrintWriter(output, true);


        } catch (IOException e) {
            System.out.println("Error getting output stream: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void run() {
        Console console = System.console();

        String userName = console.readLine("\nEnter your name: ");
        client.setUserName(userName);
        writer.println(userName);
        String text;

        do {
            text = console.readLine("[" + userName + "]: ");
            writer.println(text);
        } while (!text.equals("bye"));

        try {
            socket.close();
        } catch (IOException e) {
            System.out.println("System writing to server: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
