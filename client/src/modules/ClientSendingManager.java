package modules;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class ClientSendingManager {
    private final int PACKET_SIZE = 1024;
    private final int DATA_SIZE = PACKET_SIZE - 1;
    private final TCPclient tcpClient;

    public ClientSendingManager(TCPclient tcpClient) {
        this.tcpClient = tcpClient;
    }

    public void send(byte[] data)  {
        while (true) {
            try {
                 while (tcpClient.getSocketChannel().isConnectionPending()){
                    tcpClient.getSocketChannel().finishConnect();
                }
                while (!tcpClient.isConnected()){
                    System.out.print("Not connected, waiting..");
                    Thread.sleep(4000);
                }

                byte[][] ret = new byte[(int) Math.ceil(data.length / (double) DATA_SIZE)][DATA_SIZE];

                int start = 0;
                for (int i = 0; i < ret.length; i++) {
                    ret[i] = Arrays.copyOfRange(data, start, start + DATA_SIZE);
                    start += DATA_SIZE;
                }

//                System.out.print("Sending " + ret.length + " chunks...");

                for (int i = 0; i < ret.length; i++) {
                    var chunk = ret[i];
                    try (var outputStream = new ByteArrayOutputStream()) {
                        outputStream.write(chunk);
                        if (i == ret.length - 1) {
                            outputStream.write(new byte[]{1});
                            tcpClient.getSocketChannel().write(ByteBuffer.wrap(outputStream.toByteArray()));
//                            System.out.println("Last chunk with size " + chunk.length + " sent to the server.");
                        } else {
                            outputStream.write(new byte[]{0});
                            tcpClient.getSocketChannel().write(ByteBuffer.wrap(outputStream.toByteArray()));
//                            System.out.println("Chunk with size " + chunk.length + " sent to the server.");
                        }
                    }
                }
                return;
            }
            catch (IOException e) {
                if(!e.getMessage().equals("Connection refused: no further information"))
                    System.out.println(e);
                try{
                    Thread.sleep(200);
                    tcpClient.getSocketChannel().close();
                    tcpClient.start();
                } catch (Exception e1){}
            } catch (Exception e1){ break; }
        }
    }
}