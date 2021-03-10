package com.learning.nio;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class NIOClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 8080);
        OutputStream out = socket.getOutputStream();
        String s = "hello world";
        out.write(s.getBytes());
        out.close();
        socket.close();
    }
}
