package br.com.tp.lanchescaieiras.kitchenorder.domain;

import br.com.tp.lanchescaieiras.commons.domain.ResponseMetada;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class KitchenOrderResponseTest {

    @Test
    void testConstructorAndGetters() {
        KitchenOrder order = new KitchenOrder();
        ResponseMetada meta = new ResponseMetada();
        KitchenOrderResponse response = new KitchenOrderResponse(meta, order);

        assertEquals(meta, response.get_response());
        assertEquals(order, response.get_content());
    }

    @Test
    void testSetters() {
        KitchenOrderResponse response = new KitchenOrderResponse();
        ResponseMetada meta = new ResponseMetada();
        KitchenOrder order = new KitchenOrder();

        response.set_response(meta);
        response.set_content(order);

        assertEquals(meta, response.get_response());
        assertEquals(order, response.get_content());
    }

    @Test
    void testConstructorWithContentOnly() {
        KitchenOrder order = new KitchenOrder();
        KitchenOrderResponse response = new KitchenOrderResponse(order);
        assertNotNull(response.get_response());
        assertEquals(order, response.get_content());
    }
}
