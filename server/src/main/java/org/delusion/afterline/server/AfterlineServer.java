package org.delusion.afterline.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import javax.net.ssl.SSLServerSocket;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class AfterlineServer {
    private static final int MAX_PORT = 65535;
    private static final int DEFAULT_PORT = 9000;

    private int port;

    public AfterlineServer(int port) throws Exception {
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
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new AfterlineServerHandler());
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            // Bind and start to accept incoming connections.
            ChannelFuture f = b.bind(port).sync();

            // Wait until the server socket is closed.
            // In this example, this does not happen, but you can do that to gracefully
            // shut down your server.
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

//    private void handleConn(Socket client) throws IOException {
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
//    }


    public static void main(String[] args) throws Exception {
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
