package org.example;

import org.example.logger.Logger;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Config {

    private static Config instance;
    private static final Logger LOGGER = Logger.getInstance();
    private static final String PATH = "./config/setting.properties";

    private String host;
    private int port;

    private Config() {
        try (FileReader reader = new FileReader(PATH)) {
            Properties prop = new Properties();
            prop.load(reader);

            port = Integer.parseInt(prop.getProperty("port"));
            host = prop.getProperty("host");
        } catch (IOException e) {
            LOGGER.log("Ошибка в конструкторе класса " + Config.class.getName());
            e.printStackTrace();
        }
    }

    public static Config getInstance() {
        if (instance == null) instance = new Config();
        return instance;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }
}
