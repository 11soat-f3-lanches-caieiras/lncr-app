package br.com.tp.lanchescaieiras.customerorder.domain;

import br.com.tp.lanchescaieiras.commons.domain.ResponseMetada;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomerOrderListResponseTest {

    @Test
    void testConstructorAndGetters() {
        ResponseMetada meta = new ResponseMetada("trace", "ts", "msg");
        CustomerOrder order = new CustomerOrder();
        List<CustomerOrder> orders = List.of(order);

        CustomerOrderListResponse response = new CustomerOrderListResponse(meta, orders);

        assertEquals(meta, response.get_response());
        assertEquals(orders, response.get_content());
    }

    @Test
    void testSetters() {
        CustomerOrderListResponse response = new CustomerOrderListResponse();
        ResponseMetada meta = new ResponseMetada();
        CustomerOrder order = new CustomerOrder();
        response.set_response(meta);
        response.set_content(List.of(order));

        assertEquals(meta, response.get_response());
        assertEquals(1, response.get_content().size());
    }

    @Test
    void testConstructorWithContentOnly() {
        CustomerOrder order = new CustomerOrder();
        CustomerOrderListResponse response = new CustomerOrderListResponse(List.of(order));
        assertNotNull(response.get_response());
        assertEquals(1, response.get_content().size());
    }
}
