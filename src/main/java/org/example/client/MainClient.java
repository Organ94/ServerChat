package org.example.client;

import org.example.Config;

import java.io.BufferedReader;
import java.util.Scanner;

public class MainClient {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.print("Введите ваш никнейм\n>> ");
        String nickName = SCANNER.nextLine();

        Config config = Config.getInstance();
        Client client = new Client(nickName, config.getHost(), config.getPort());
        new Thread(client).start();
    }
}
