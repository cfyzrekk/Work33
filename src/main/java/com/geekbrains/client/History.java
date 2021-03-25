package com.geekbrains.client;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class History {
    private static final int LINES = 100;

    public static void oldMessage(String login, String message) throws IOException {
        Files.write(historyPath(login), message.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }

    private static Path historyPath(String login) throws IOException {
        Path hsPath = Paths.get("history", login + "history.txt");
        if(Files.notExists(hsPath.getParent())){
            createDirect(hsPath);
        }
        return hsPath;
    }
    public static void createDirect(Path path) throws IOException {
        Files.createDirectory(path.getParent());
    }

    public static String lastMessages(String login) throws IOException {
        Path historyPathFile = historyPath(login);
        if(Files.notExists(historyPathFile)) {
            return "";
        }
        List<String> line = Files.readAllLines(historyPathFile);
        return getLastMessage(line);

    }
    private static String getLastMessage(List<String> line) {
        StringBuilder done = new StringBuilder();
        int firstLine = 0;
        if(line.size() > LINES) {
            firstLine = line.size() - LINES;
        }
        for (int i = firstLine; i < line.size(); i++) {
            done.append(line.get(i)).append("\r\n");
        }
        return done.toString();
    }


}
