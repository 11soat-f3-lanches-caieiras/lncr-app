package br.com.tp.lanchescaieiras.payments.mercadopago.domain;

import br.com.tp.lanchescaieiras.commons.domain.ResponseMetada;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaymentResponseTest {

    @Test
    void testConstructorAndGettersSetters() {
        Payment payment = new Payment(1, 2, 10.0, null, null, "Paid", "pid");
        ResponseMetada meta = new ResponseMetada();
        PaymentResponse response = new PaymentResponse(meta, payment);

        assertEquals(meta, response.get_response());
        assertEquals(payment, response.getPayment());

        Payment payment2 = new Payment(2, 3, 20.0, null, null, "Charged", "pid2");
        response.setPayment(payment2);
        assertEquals(payment2, response.getPayment());

        ResponseMetada meta2 = new ResponseMetada();
        response.set_response(meta2);
        assertEquals(meta2, response.get_response());
    }

    @Test
    void testConstructorWithPaymentOnly() {
        Payment payment = new Payment(1, 2, 10.0, null, null, "Paid", "pid");
        PaymentResponse response = new PaymentResponse(payment);

        assertNotNull(response.get_response());
        assertEquals(payment, response.getPayment());
    }

    @Test
    void testDefaultConstructor() {
        PaymentResponse response = new PaymentResponse();
        assertNull(response.get_response());
        assertNull(response.getPayment());
    }
}
