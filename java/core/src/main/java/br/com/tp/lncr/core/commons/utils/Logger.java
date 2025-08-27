package br.com.tp.lncr.core.commons.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    public enum Level {
        INFO, DEBUG, ERROR
    }

    private static final String LOG_FILE = "../../application.log";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
    private static final String PID = getProcessId();

    public static void log(Level level, String message) {
        String logMessage = String.format("%s  %-5s %s --- [%s] %s : %s",
                ZonedDateTime.now().format(FORMATTER),
                level,
                PID,
                Thread.currentThread().getName(),
                Logger.class.getSimpleName(),
                message);
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

    private static String getProcessId() {
        String jvmName = java.lang.management.ManagementFactory.getRuntimeMXBean().getName();
        int index = jvmName.indexOf('@');
        if (index > 0) {
            return jvmName.substring(0, index);
        } else {
            return "-";
        }
    }
}
