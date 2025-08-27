package br.com.tp.lncr.core.domain.customer;

import br.com.tp.lncr.core.commons.exceptions.CustomerException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CustomerEmailTest {
    @Test
    void testValidEmail() {
        CustomerEmail email = new CustomerEmail("teste@email.com");
        Assertions.assertEquals("teste@email.com", email.getValue());
    }

    @Test
    void testInvalidEmailThrowsException() {
        Assertions.assertThrows(CustomerException.class, () -> new CustomerEmail("email-invalido"));
    }

    @Test
    void testNullEmailThrowsException() {
        Assertions.assertThrows(CustomerException.class, () -> new CustomerEmail(null));
    }

    @Test
    void testToString() {
        CustomerEmail email = new CustomerEmail("teste@email.com");
        Assertions.assertTrue(email.toString().contains("CustomerEmail{"));
    }
}

