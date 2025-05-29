package br.com.tp.lanchescaieiras.commons.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResponseMetadaTest {

    @Test
    void testDefaultConstructor() {
        ResponseMetada response = new ResponseMetada();
        assertNotNull(response.get_traceId());
        assertNotNull(response.get_timestamp());
        assertNull(response.get_message());
    }

    @Test
    void testConstructorWithAllFields() {
        ResponseMetada response = new ResponseMetada("trace", "timestamp", "mensagem");
        assertEquals("trace", response.get_traceId());
        assertEquals("timestamp", response.get_timestamp());
        assertEquals("mensagem", response.get_message());
    }

    @Test
    void testConstructorWithoutMessage() {
        ResponseMetada response = new ResponseMetada("trace2", "timestamp2");
        assertEquals("trace2", response.get_traceId());
        assertEquals("timestamp2", response.get_timestamp());
        assertNull(response.get_message());
    }

    @Test
    void testSetters() {
        ResponseMetada response = new ResponseMetada();
        response.set_traceId("id");
        response.set_timestamp("ts");
        response.set_message("msg");

        assertEquals("id", response.get_traceId());
        assertEquals("ts", response.get_timestamp());
        assertEquals("msg", response.get_message());
    }
}
