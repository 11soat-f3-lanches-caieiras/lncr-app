package br.com.tp.lncr.core.commons.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class LoggerTest {
    @Test
    void testInfoLog() {
        assertDoesNotThrow(() -> Logger.info("Mensagem de info"));
    }

    @Test
    void testDebugLog() {
        assertDoesNotThrow(() -> Logger.debug("Mensagem de debug"));
    }

    @Test
    void testErrorLog() {
        assertDoesNotThrow(() -> Logger.error("Mensagem de erro"));
    }

    @Test
    void testLogWithLevel() {
        assertDoesNotThrow(() -> Logger.log(Logger.Level.INFO, "Teste com level"));
    }
}

