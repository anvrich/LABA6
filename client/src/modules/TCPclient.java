package modules;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SocketChannel;

public class TCPclient {
    private static final int BUFFER_SIZE = 1024;
    private static SocketChannel socketChannel;

    public TCPclient(String host, int port){
        try {
            socketChannel = SocketChannel.open();
            socketChannel.connect(new InetSocketAddress(host, port));

        } catch (Exception e) {
            System.out.println("Server is offline!");
            System.exit(0);
        }
    }

    public static void handleRead() throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
        int read = -1;
        try {
            read = socketChannel.read(buffer);
        } catch (SocketException | ClosedChannelException se) {
            System.out.println("server die (");
            System.exit(0);
        }

        if (read == -1) {
            socketChannel.close();
            return;
        } // socketChanal closing.
        buffer.flip();

        ByteArrayInputStream bais = new ByteArrayInputStream(buffer.array());
        ObjectInputStream ois = new ObjectInputStream(bais);
        Response response = null;
        try {
            response = (Response) ois.readObject();
        } catch (ClassNotFoundException e) {
            System.out.println("server die (");
            System.exit(0);
        }
        System.out.println("Server: " + response.getResult());
    }

    public static void sendCommand(Request request) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(request);

        System.out.println(request.toString() + " from client");

        ByteBuffer buffer = ByteBuffer.wrap(baos.toByteArray());
        try{
            socketChannel.write(buffer);
        } catch (IOException ioe) {
            System.out.println("server die (");
            System.exit(0);
        }
        buffer.clear();
    }
}
