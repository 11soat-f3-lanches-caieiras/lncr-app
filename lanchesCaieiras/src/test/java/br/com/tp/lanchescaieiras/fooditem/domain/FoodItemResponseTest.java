package br.com.tp.lanchescaieiras.fooditem.domain;

import br.com.tp.lanchescaieiras.commons.domain.ResponseMetadata;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class FoodItemResponseTest {

    @Test
    void testConstructorAndGetters() {
        ResponseMetadata meta = new ResponseMetadata("trace", "ts", "msg");
        FoodItem item = new FoodItem();
        FoodItemResponse response = new FoodItemResponse(meta, item);

        assertEquals(meta, response.get_response());
        assertEquals(item, response.get_content());
    }

    @Test
    void testConstructorWithContentOnly() {
        FoodItem item = new FoodItem();
        FoodItemResponse response = new FoodItemResponse(item);

        assertNotNull(response.get_response());
        assertEquals(item, response.get_content());
    }

    @Test
    void testSetters() {
        FoodItemResponse response = new FoodItemResponse();
        ResponseMetadata meta = new ResponseMetadata();
        FoodItem item = new FoodItem();
        response.set_response(meta);
        response.set_content(item);

        assertEquals(meta, response.get_response());
        assertEquals(item, response.get_content());
    }
}
