package br.com.tp.lncr.core.domain.customer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CustomerTest {
    @Test
    void testConstructorAndGetters() {
        Customer customer = new Customer(1, "12345678909", "João", "joao@email.com");
        Assertions.assertEquals(1, customer.getId());
        Assertions.assertEquals("12345678909", customer.getDocumentNumber());
        Assertions.assertEquals("João", customer.getName());
        Assertions.assertEquals("joao@email.com", customer.getEmail());
    }

    @Test
    void testSetters() {
        Customer customer = new Customer();
        customer.setId(2);
        customer.setDocumentNumber("98765432100");
        customer.setName("Maria");
        customer.setEmail("maria@email.com");
        Assertions.assertEquals(2, customer.getId());
        Assertions.assertEquals("98765432100", customer.getDocumentNumber());
        Assertions.assertEquals("Maria", customer.getName());
        Assertions.assertEquals("maria@email.com", customer.getEmail());
    }

    @Test
    void testDocumentNumberIsValid() {
        Customer customer = new Customer(1, "12345678909", "João", "joao@email.com");
        Assertions.assertTrue(customer.documentNumberIsValid());
    }

    @Test
    void testEmailIsValid() {
        Customer customer = new Customer(1, "12345678909", "João", "joao@email.com");
        Assertions.assertTrue(customer.emailIsValid());
    }

    @Test
    void testToString() {
        Customer customer = new Customer(1, "12345678909", "João", "joao@email.com");
        String str = customer.toString();
        Assertions.assertTrue(str.contains("Customer{"));
    }
}

