package org.example.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private static final ExecutorService threadPool = Executors.newFixedThreadPool(2);
    private ServerHandler serverHandler;
    private static final List<ServerHandler> users = Collections.synchronizedList(new ArrayList<>());

    public void listen(String host, int port) {
        try (ServerSocketChannel server = ServerSocketChannel.open()) {
            server.bind(new InetSocketAddress(host, port));
            System.out.println("Server started!");

            while (true) {
                SocketChannel socket = server.accept();
                threadPool.submit(serverHandler = new ServerHandler(socket, this));
                users.add(serverHandler);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void connectedClient(SocketChannel client) {
        try {
            if (client.isConnected()) {
                System.out.println("Client " + client.getLocalAddress() + " connected!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessageToAllUsers(String msg) throws IOException {
        for (ServerHandler user : users) {
            user.sendMessage(msg);
        }
    }

    public void removeUser(ServerHandler serverHandler, SocketChannel user) throws IOException {
        System.out.println("User " + user.getLocalAddress() + " disconnecting");
        users.remove(serverHandler);
    }
}
