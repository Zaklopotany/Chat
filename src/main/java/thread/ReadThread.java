package thread;

import user.ChatClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by mzukowski on 09/04/2018.
 */
public class ReadThread extends Thread {
    private BufferedReader reader;
    private Socket socket;
    private ChatClient client;

    public ReadThread(Socket socket, ChatClient chatClient) {
        this.socket = socket;
        this.client = client;

        try {
            InputStream inputStream = socket.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream));

        } catch (IOException e1) {
            System.out.println("Error getting input stream: " + e1.getMessage());
            e1.printStackTrace();
        }
    }

    public void run() {
        while (true) {
            try {
                String response = reader.readLine();
                System.out.println("\n" +response);

                if (client.getUserName() != null) {
                    System.out.println("[" + client.getUserName() + "]: ");
                }


            } catch (IOException e) {
                System.out.println("Error reading from server: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

}
