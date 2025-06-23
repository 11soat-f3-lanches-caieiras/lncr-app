package br.com.tp.lanchescaieiras.kitchenorder.domain;

import br.com.tp.lanchescaieiras.commons.domain.ResponseMetadata;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class KitchenOrderListResponseTest {

    @Test
    void testConstructorAndGetters() {
        KitchenOrder order = new KitchenOrder();
        ResponseMetadata meta = new ResponseMetadata();
        KitchenOrderListResponse response = new KitchenOrderListResponse(meta, List.of(order));

        assertEquals(meta, response.get_response());
        assertEquals(1, response.get_content().size());
    }

    @Test
    void testSetters() {
        KitchenOrderListResponse response = new KitchenOrderListResponse();
        ResponseMetadata meta = new ResponseMetadata();
        KitchenOrder order = new KitchenOrder();

        response.set_response(meta);
        response.set_content(List.of(order));

        assertEquals(meta, response.get_response());
        assertEquals(1, response.get_content().size());
    }

    @Test
    void testConstructorWithContentOnly() {
        KitchenOrder order = new KitchenOrder();
        KitchenOrderListResponse response = new KitchenOrderListResponse(List.of(order));
        assertNotNull(response.get_response());
        assertEquals(1, response.get_content().size());
    }
}
