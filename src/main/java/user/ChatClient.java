package user;

import thread.ReadThread;
import thread.WriteThread;

import javax.xml.soap.Text;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by mzukowski on 09/04/2018.
 */
public class ChatClient {
    private String hostName;
    private int port;
    private String userName;




    public void setUserName(String userName) {
        this.userName = userName;
    }



    public ChatClient(String hostName, int port) {
        this.hostName = hostName;
        this.port = port;
    }

    public void execute() {
        try {
            Socket socket = new Socket(hostName, port);
            TextDemo.setSocket(socket);
            System.out.println("Connected to sex dates chat server");
            new Thread(new Runnable() {
                public void run() {
                    TextDemo.createAndShowGUI();
                }
            }).start();
            new ReadThread(socket, this).start();

        } catch (UnknownHostException e) {
            System.out.println("Server not found : " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("I/O problem : " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
//        if(args.length < 2) return;
        String hostname = "10.141.111.61";
        int port = 7;
        ChatClient client = new ChatClient(hostname, port);
        client.execute();



    }



}
