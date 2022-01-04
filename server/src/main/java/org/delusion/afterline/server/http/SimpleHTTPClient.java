package org.delusion.afterline.server.http;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLException;
import javax.net.ssl.X509TrustManager;

import org.json.JSONObject;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.SslHandler;
import io.netty.handler.ssl.SslProvider;

public class SimpleHTTPClient {

    public SimpleHTTPClient(URI targetURI, HTTPRequest request, BiConsumer<Channel, HTTPResponse> callback) {
        try {
            // String certs_f_content = "";
            // try(BufferedReader br = new BufferedReader(new FileReader("certs.json"))) {
            //     StringBuilder sb = new StringBuilder();
            //     String line = br.readLine();
            
            //     while (line != null) {
            //         sb.append(line);
            //         sb.append(System.lineSeparator());
            //         line = br.readLine();
            //     }

            //     certs_f_content = sb.toString();

            // } catch (IOException e) {
            //     SimpleHTTPServer.LOGGER.catching(e);
            // }

            // JSONObject obj = new JSONObject(certs_f_content);
            // List<String> certs_to_add = obj.keySet().stream().map(obj::getString).collect(Collectors.toList());

            EventLoopGroup workerGroup = new NioEventLoopGroup();

            SslContext sslContext = SslContextBuilder
                    .forClient()
                    .sslProvider(SslProvider.OPENSSL)
                    // .trustManager(new File("google-com.pem"))
                    .build();

            SSLEngine sslEngine = sslContext.newEngine(PooledByteBufAllocator.DEFAULT);

            try {
                Bootstrap b = new Bootstrap();
                b.group(workerGroup);
                b.channel(NioSocketChannel.class);
                b.option(ChannelOption.SO_KEEPALIVE, true);
                b.handler(new ChannelInitializer<SocketChannel>() {

                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new SslHandler(sslEngine));
                        ch.pipeline().addLast(new SimpleHTTPClientHandler(callback));
                    }
                });

                ChannelFuture f = b.connect(targetURI.getHost(), 443).sync();

                
                SimpleHTTPServer.LOGGER.info("connected!");

                request.getHeaders().set(HTTPHeaderField.Host, targetURI.getHost());
                String req = request.toString();

                SimpleHTTPServer.LOGGER.debug(req);
                

                ByteBuf buffer = f.channel().alloc().buffer(req.length() * 2);
                buffer.writeBytes(req.getBytes());
                
                f.channel().writeAndFlush(buffer).sync();
                
                f.channel().closeFuture().sync();
            } catch (InterruptedException e) {
                SimpleHTTPServer.LOGGER.catching(e);
            } finally {
                workerGroup.shutdownGracefully();
            }
        } catch (SSLException e) {
            SimpleHTTPServer.LOGGER.catching(e);
        }
    }


    static class SimpleHTTPClientHandler extends ChannelInboundHandlerAdapter {

        private BiConsumer<Channel, HTTPResponse> callback;
        private StringBuilder currentMessage = new StringBuilder();

        public SimpleHTTPClientHandler(BiConsumer<Channel, HTTPResponse> callback) {
            this.callback = callback;
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            ByteBuf buf = (ByteBuf)msg;
            currentMessage.append(buf.getCharSequence(0, buf.readableBytes(), StandardCharsets.UTF_8));
            buf.release();
        }

        @Override
        public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
            String msg = currentMessage.toString();
            if (msg.isEmpty()) {
                SimpleHTTPServer.LOGGER.debug("Received a blank response, ignoring");
                return;
            }

            SimpleHTTPServer.LOGGER.debug(msg);

            HTTPResponse resp = new HTTPResponse(msg);
            callback.accept(ctx.channel(), resp);
        }
    }

}
