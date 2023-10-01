package modules;

import java.io.*;
import java.net.*;
import java.nio.*;
import java.nio.channels.*;
import java.util.Scanner;

public class TCPclient {
    private static final int BUFFER_SIZE = 1024;
    private InetSocketAddress host;

    private static SocketChannel socketChannel;
    private static ClientReceivingManager receivingManager = null;
    private static ClientSendingManager sendingManager = null;

    public TCPclient(String host, int port){
        this.host = new InetSocketAddress(host, port);
    }

    public SocketChannel start() {
        while(true){
            try {
                if(socketChannel != null) {
                    socketChannel.close();
                }
                this.socketChannel = SocketChannel.open();
                socketChannel.bind(new InetSocketAddress("127.0.0.1", 20000+(int)(Math.random()*10000)));
                socketChannel.configureBlocking(false);
                socketChannel.connect(this.host);
                receivingManager = new ClientReceivingManager(this);
                sendingManager = new ClientSendingManager(this);
                return socketChannel;
            } catch (Exception e) {
                try{
                    System.out.println("TCP client: " + e.getMessage());
                    socketChannel.close();
                }
                catch (Exception e2){
                    System.out.println("TCP client: " + e2.getMessage());
                }
            }
        }
    }

    public static void sendCommand(Request request) throws IOException {

        try (var baos = new ByteArrayOutputStream(); var a=new ObjectOutputStream(baos)) {
            a.writeObject(request);
            sendingManager.send(baos.toByteArray());

            try(var command = new ObjectInputStream(new ByteArrayInputStream(receivingManager.receive()))) {
                Response response = (Response) command.readObject();
                System.out.println(response.getResult());
            }
        } catch (Exception e) {
            System.out.println("\nsendCommand - " + e);
        }
    }
    public SocketChannel getSocketChannel () {
        return this.socketChannel;
    }
    public boolean isConnected() {
        return socketChannel != null && socketChannel.socket().isBound() && !socketChannel.socket().isClosed() && socketChannel.isConnected()
                && !socketChannel.socket().isInputShutdown() && !socketChannel.socket().isOutputShutdown();
    }
}
