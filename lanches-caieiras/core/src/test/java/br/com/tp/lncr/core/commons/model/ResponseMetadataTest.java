package br.com.tp.lncr.core.commons.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResponseMetadataTest {
    @Test
    void testDefaultConstructor() {
        ResponseMetadata meta = new ResponseMetadata();
        assertNotNull(meta.get_traceId());
        assertNotNull(meta.get_timestamp());
        assertNull(meta.get_message());
    }

    @Test
    void testConstructorWithAllFields() {
        ResponseMetadata meta = new ResponseMetadata("trace", "2025-07-18T12:00:00Z", "msg");
        assertEquals("trace", meta.get_traceId());
        assertEquals("2025-07-18T12:00:00Z", meta.get_timestamp());
        assertEquals("msg", meta.get_message());
    }

    @Test
    void testConstructorWithoutMessage() {
        ResponseMetadata meta = new ResponseMetadata("trace2", "2025-07-18T13:00:00Z");
        assertEquals("trace2", meta.get_traceId());
        assertEquals("2025-07-18T13:00:00Z", meta.get_timestamp());
        assertNull(meta.get_message());
    }

    @Test
    void testSettersAndGetters() {
        ResponseMetadata meta = new ResponseMetadata();
        meta.set_traceId("id");
        meta.set_timestamp("2025-07-18T14:00:00Z");
        meta.set_message("mensagem");
        assertEquals("id", meta.get_traceId());
        assertEquals("2025-07-18T14:00:00Z", meta.get_timestamp());
        assertEquals("mensagem", meta.get_message());
    }
}

