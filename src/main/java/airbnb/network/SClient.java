package airbnb.network;

import airbnb.controller.Handler;
import airbnb.view.StartView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class SClient {
    private final String ip;
    private final int port;

    private Socket socket;

    private ObjectOutputStream objectOutputStream;

    private ObjectInputStream objectInputStream;

    private Handler handler;
    private BufferedReader bufferReader;

    Scanner sc = new Scanner(System.in);

    public SClient(String ip, int port) {
        this.ip = ip;
        this.port = port;
        try {
            socket = new Socket(ip, port);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void run() {
        try {
            System.out.println("client run");
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            handler = new Handler(objectOutputStream, objectInputStream);
            Protocol protocol = new Protocol();
            while (true) {
                new StartView().run(handler, protocol);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}