package br.com.tp.lanchescaieiras._core.commons.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    public enum Level {
        INFO, DEBUG, ERROR
    }

    private static final String LOG_FILE = "application.log";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void log(Level level, String message) {
        String logMessage = String.format("[%s] [%s] %s", LocalDateTime.now().format(FORMATTER), level, message);
        System.out.println(logMessage);
        writeToFile(logMessage);
    }

    public static void info(String message) {
        log(Level.INFO, message);
    }

    public static void debug(String message) {
        log(Level.DEBUG, message);
    }

    public static void error(String message) {
        log(Level.ERROR, message);
    }

    private static void writeToFile(String message) {
        try (FileWriter fw = new FileWriter(LOG_FILE, true); PrintWriter pw = new PrintWriter(fw)) {
            pw.println(message);
        } catch (IOException e) {
            System.err.println("Erro ao escrever no arquivo de log: " + e.getMessage());
        }
    }
}

