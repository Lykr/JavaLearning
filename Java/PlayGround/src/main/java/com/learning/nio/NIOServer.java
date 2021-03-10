package com.learning.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NIOServer {
    public static void main(String[] args) {
        try {
            Selector selector = Selector.open();
            ServerSocketChannel ssChannel = ServerSocketChannel.open();
            ssChannel.configureBlocking(false);
            ssChannel.register(selector, SelectionKey.OP_ACCEPT);

            ServerSocket serverSocket = ssChannel.socket();
            InetSocketAddress serverSocketAddress = new InetSocketAddress("localhost", 8080);
            serverSocket.bind(serverSocketAddress);

            while (true) {
                selector.select();
                Set<SelectionKey> set = selector.selectedKeys();
                Iterator<SelectionKey> iterator = set.iterator();

                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();

                    System.out.println("SelectionKey: " + key.toString());

                    if (key.isAcceptable()) {
                        ServerSocketChannel ssChannel0 = (ServerSocketChannel) key.channel();

                        SocketChannel socketChannel = ssChannel0.accept();
                        socketChannel.configureBlocking(false);

                        socketChannel.register(selector, SelectionKey.OP_READ);
                    } else if (key.isReadable()) {
                        SocketChannel socketChannel = (SocketChannel) key.channel();
                        System.out.println(readDataFromSocketChannel(socketChannel));
                        socketChannel.close();
                    }

                    iterator.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String readDataFromSocketChannel(SocketChannel channel) {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        StringBuffer sb = new StringBuffer();

        while (true) {
            try {
                int n = channel.read(buffer);
                System.out.println("n == " + n);
                if (n == -1) break;
                buffer.flip();
                System.out.println("buffer.filp()");
                for (int i = 0; i < buffer.limit(); i++) {
                    sb.append((char)buffer.get());
                }
                buffer.clear();
                System.out.println("buffer.clear()");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return sb.toString();
    }
}
