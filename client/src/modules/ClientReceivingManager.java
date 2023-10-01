package modules;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Objects;

public class ClientReceivingManager {

    private byte[] receivedData;
    private final int DATA_CHUNK = 1024;
    private final TCPclient tcpClient ;

    public ClientReceivingManager(TCPclient tcpClient) {
        this.tcpClient= tcpClient;
        this.receivedData = new byte[0];
    }

    public byte[] receive() {
        receivedData = new byte[0];
        while (true){
            try {
                if(tcpClient.getSocketChannel() == null)
                    continue;
                // true - when client is trying to connect to the server in non-blocking mode
                // and if specified host does not exist, finishConnect() will throw error when first time called and close the connection

                while (tcpClient.getSocketChannel().isConnectionPending()){
                    tcpClient.getSocketChannel().finishConnect();
                }
                // if because of called finishConnect the connection got closed we are trying to make another connection

                if(!tcpClient.getSocketChannel().isOpen()){
                    tcpClient.start();
                    Thread.sleep(3000);
                    continue;
                }
                ByteBuffer byteBuffer = ByteBuffer.allocate(DATA_CHUNK);
                var readBytes = tcpClient.getSocketChannel().read(byteBuffer);
                if(readBytes == 0) {
                    Thread.sleep(50);
                    continue;
                }
                if(readBytes == -1)

                    tcpClient.getSocketChannel().close();

                ByteArrayOutputStream outputStream = new ByteArrayOutputStream( );
                outputStream.write(receivedData);
                outputStream.write(Arrays.copyOf(byteBuffer.array(), byteBuffer.array().length - 1));
                receivedData = outputStream.toByteArray();
                // reached the end of the object being sent
                if (byteBuffer.array()[readBytes - 1] == 1) {
                    return receivedData;
                }
                byteBuffer.clear();
            }
            catch (Exception e ) {
                // server has crashed, and we got isConnectionPending - true and isOpen - true as well
                // and so we close the connection, because this channel is left by the server and create another one
                if(Objects.equals(e.getMessage(), "Connection reset"))
                {
                    try{
                        Thread.sleep(3000);
                        tcpClient.getSocketChannel().close();
                        tcpClient.start();
                    } catch (Exception e1){}
                }
            }
        }
    }
}