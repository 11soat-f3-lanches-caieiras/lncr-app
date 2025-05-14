package br.com.tp.lanchescaieiras.customer.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;

import static org.junit.jupiter.api.Assertions.*;
class CustomerTest {

    @Test
    void custructorNoArgs(){
        Customer customer = new Customer();
        assertNotNull(customer);
    }

    @Test
    void constructorWithArgs() {
        Customer customer = new Customer(1, "15826123907", "Teste da Silva", "email@email.com.br");
        assertNotNull(customer);
    }

    @Test
    void documentNumberIsValid_Valid() {
        Customer customer = new Customer();
        customer.setDocumentNumber("15826123907");
        assertTrue(customer.documentNumberIsValid());
    }

    @Test
    void documentNumberIsValid_Invalid() {
        Customer customer = new Customer();
        customer.setDocumentNumber("111111111111");
        assertFalse(customer.documentNumberIsValid());
    }

    @Test
    void emailIsValid_Valid() {
        Customer customer = new Customer();
        customer.setEmail("email@email.com.br");
        assertTrue(customer.emailIsValid());
        customer.setEmail("email@email.com");
        assertTrue(customer.emailIsValid());
    }
    @Test
    void emailIsValid_Invalid() {
        Customer customer = new Customer();
        customer.setEmail("email.email.com.br");
        assertFalse(customer.emailIsValid());

    }
}