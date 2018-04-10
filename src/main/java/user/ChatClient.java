package user;

import thread.ReadThread;
import thread.WriteThread;

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

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public int getPort() {
        return port;
    }

    public String getHostName() {
        return hostName;
    }

    public ChatClient(String hostName, int port) {
        this.hostName = hostName;
        this.port = port;
    }

    public void execute() {
        try {
            Socket socket = new Socket(hostName, port);

            System.out.println("Connected to sex dates chat server");
            new ReadThread(socket, this).start();
            new WriteThread(socket, this).start();

        } catch (UnknownHostException e) {
            System.out.println("Server not found : " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("I/O problem : " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        if(args.length < 2) return;
        String hostname = args[0];
        int port = Integer.parseInt(args[1]);
        ChatClient client = new ChatClient(hostname, port);
        client.execute();



    }



}
