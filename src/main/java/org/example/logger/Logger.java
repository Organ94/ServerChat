package org.example.logger;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {

    private static final String PATH = "./src/main/java/org/example/logger/MessageFromUsers.txt";

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
    private static Logger instance;

    public Logger() {
    }

    public boolean log(String msg) {
        try {
            String message = "[" + LocalDateTime.now().format(formatter) + "] " + msg;
            PrintWriter writer = new PrintWriter(new FileWriter(PATH, true));
            writer.write(message);
            writer.flush();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Logger getInstance() {
        if (instance == null) instance = new Logger();
        return instance;
    }
}
