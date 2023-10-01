package modules;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;

public class ReceivingManager {
    private HashSet<SocketChannel> sessions = new HashSet<>();
    private final HashMap<SocketAddress, byte[]> receivedData;

    public ReceivingManager(){
        this.receivedData = new HashMap<>();
    }

    public byte[] read(SelectionKey key) {
        SocketChannel channel = (SocketChannel) key.channel();
        try {
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            int numRead = channel.read(byteBuffer);
            if (numRead == -1) {
                this.sessions.remove(channel);
                channel.close();
                key.cancel();
                return null;
            }
            var clientSocket = channel.socket();
            var arr = receivedData.get(clientSocket.getRemoteSocketAddress());
            arr = arr == null ? new byte[0] : arr;
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream( );
            outputStream.write(arr);
            outputStream.write(Arrays.copyOf(byteBuffer.array(), byteBuffer.array().length - 1));
            arr = outputStream.toByteArray();
            if(byteBuffer.array()[numRead-1] == 1){
                receivedData.put(clientSocket.getRemoteSocketAddress(), new byte[0]);
                return arr;
            }
            receivedData.put(clientSocket.getRemoteSocketAddress(), arr);
        }
        catch (IOException e){
            System.out.println(e.getMessage());
            if(Objects.equals(e.getMessage(), "Connection reset"))
            {
                try{
                    Thread.sleep(500);
                    channel.close();
                } catch (Exception e1){}
            }
        }
        return null;
    }
}