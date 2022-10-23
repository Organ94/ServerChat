package org.example.server;

import org.example.Config;

public class MainServer {

    public static void main(String[] args) {
        Config config = Config.getInstance();
        Server server = new Server();
        server.listen(config.getHost(), config.getPort());
    }
}
