package br.com.tp.lanchescaieiras.commons.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResponseMetadataTest {

    @Test
    void testDefaultConstructor() {
        ResponseMetadata response = new ResponseMetadata();
        assertNotNull(response.get_traceId());
        assertNotNull(response.get_timestamp());
        assertNull(response.get_message());
    }

    @Test
    void testConstructorWithAllFields() {
        ResponseMetadata response = new ResponseMetadata("trace", "timestamp", "mensagem");
        assertEquals("trace", response.get_traceId());
        assertEquals("timestamp", response.get_timestamp());
        assertEquals("mensagem", response.get_message());
    }

    @Test
    void testConstructorWithoutMessage() {
        ResponseMetadata response = new ResponseMetadata("trace2", "timestamp2");
        assertEquals("trace2", response.get_traceId());
        assertEquals("timestamp2", response.get_timestamp());
        assertNull(response.get_message());
    }

    @Test
    void testSetters() {
        ResponseMetadata response = new ResponseMetadata();
        response.set_traceId("id");
        response.set_timestamp("ts");
        response.set_message("msg");

        assertEquals("id", response.get_traceId());
        assertEquals("ts", response.get_timestamp());
        assertEquals("msg", response.get_message());
    }
}
