package br.com.tp.lncr.core.domain.customer;

import br.com.tp.lncr.core.commons.exceptions.CustomerException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CustomerCPFTest {
    @Test
    void testValidCPF() {
        CustomerCPF cpf = new CustomerCPF("12345678909");
        Assertions.assertEquals("12345678909", cpf.getValue());
    }

    @Test
    void testInvalidCPFThrowsException() {
        Assertions.assertThrows(CustomerException.class, () -> new CustomerCPF("11111111111"));
    }

    @Test
    void testNullCPFThrowsException() {
        Assertions.assertThrows(CustomerException.class, () -> new CustomerCPF(null));
    }

    @Test
    void testToString() {
        CustomerCPF cpf = new CustomerCPF("12345678909");
        Assertions.assertTrue(cpf.toString().contains("CustomerCPF{"));
    }
}

