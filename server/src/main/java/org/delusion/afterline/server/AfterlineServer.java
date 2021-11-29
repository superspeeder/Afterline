package org.delusion.afterline.server;

import javax.net.ssl.SSLServerSocket;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class AfterlineServer {
    private static final int MAX_PORT = 65535;
    private static final int DEFAULT_PORT = 9000;

    private int port;

    public AfterlineServer(int port) {
        this.port = port;

//        try {
//            ServerSocket socket = new ServerSocket(port);
//            System.out.println("Opened server on port " + port);
//
//            while (true) {
//                Socket client = socket.accept();
//                new Thread(() -> {
//                    try {
//                        handleConn(client);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }).start();
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    private void handleConn(Socket client) throws IOException {
//        String cn = client.toString();
//        System.out.println("Client connected from " + cn);
//
//        DataOutputStream out = new DataOutputStream(client.getOutputStream());
//        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
//
//        int color = 0xFF00FFFF;
//        out.writeInt(color);
//        while (!client.isClosed()) {
//            if(client.getInputStream().read() == -1){
//                client.close();
//                break;
//            }
//            Thread.yield();
////            System.out.println("Client " + cn + " is still connected!");
//        }
//        System.out.println("Client disconnected from " + cn);
    }


    public static void main(String[] args) {
        System.out.println(Arrays.toString(args));
        int port = DEFAULT_PORT;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
            if (port < 0 || port > MAX_PORT) {
                port = DEFAULT_PORT;
            }
        }

        new AfterlineServer(port);
    }
}
