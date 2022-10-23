package org.example.server;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class ServerHandler implements Runnable {

    private SocketChannel socket;
    private Server server;

    public ServerHandler(SocketChannel socket, Server server) {
        this.socket = socket;
        this.server = server;
    }

    @Override
    public void run() {
        server.connectedClient(socket);
        try {
            final ByteBuffer inputBuffer = ByteBuffer.allocate(2 << 10);

            while (true) {
                int byteCount = socket.read(inputBuffer);

                if (byteCount == -1) {
                    break;
                }

                final String msg = new String(inputBuffer.array(), 0, byteCount, StandardCharsets.UTF_8);

                if (msg.equalsIgnoreCase("/exit")) {
                    break;
                }

                server.sendMessageToAllUsers(msg);
                inputBuffer.clear();
                Thread.sleep(100);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                this.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void close() throws IOException {
        server.removeUser(this, socket);
    }

    public void sendMessage(String msg) throws IOException {
        socket.write(ByteBuffer.wrap(
                (msg + "\n").getBytes(StandardCharsets.UTF_8)
        ));
    }
}
