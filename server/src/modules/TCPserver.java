package modules;

import commands.Command;
import commands.CommandManager;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class TCPserver {
    private static final int BUFFER_SIZE = 1024;
    private static Selector selector;
    private static ServerSocketChannel serverSocketChannel;

    public TCPserver(String host, int port) {
        try {
            selector = Selector.open();
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.socket().bind(new InetSocketAddress(host, port));
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run(){
        try {
            while (true) {
                selector.select();
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();

                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    iterator.remove();
                    if (key.isAcceptable()) {
                        handleAccept(key);
                    } else if (key.isReadable()) {
                        handleRead(key);
                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Ошибка: " + e);
        }

    }

    private static void handleAccept(SelectionKey key) throws IOException {
        ServerSocketChannel ssChannel = (ServerSocketChannel) key.channel();
        SocketChannel sChannel = ssChannel.accept();
        sChannel.configureBlocking(false);
        sChannel.register(selector, SelectionKey.OP_READ);
        System.out.println("Новый Клиент подключислся!");
    }

    private static void handleRead(SelectionKey key) throws IOException, ClassNotFoundException {
        SocketChannel sChannel = (SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
        int read = -1;
        try{
            read = sChannel.read(buffer);
        } catch (SocketException se) {
            System.out.println("one of the clients disconnected!");
        }


        if (read == -1) {
            sChannel.close();
            return;
        }

        buffer.flip();
        ByteArrayInputStream bais = new ByteArrayInputStream(buffer.array());
        ObjectInputStream ois = new ObjectInputStream(bais);
        Request request = (Request) ois.readObject();
        System.out.println(request.toString()+" from server ");

        Command cmd = CommandManager.getComands().get(request.getCommand());

        if(cmd.isWithArgument()) {
            cmd.setArgument(request.getArg());
        }
        cmd.setPerson(request.getPerson());


        Response response = cmd.execute();

        buffer.clear();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(response);
        buffer.put(baos.toByteArray());
        buffer.flip();

        while (buffer.hasRemaining()) {
            sChannel.write(buffer);
        }
    }

    private static Response processCommand(Request request) {
        // Имплементация обработки команды
        String result = "Processed command: " + request.getCommand();
        return new Response(result);
    }
}
