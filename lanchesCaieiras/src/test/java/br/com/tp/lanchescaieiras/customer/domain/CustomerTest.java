package br.com.tp.lanchescaieiras.customer.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    @Test
    void documentNumberIsValidWithValidCPF() {
        Customer customer = new Customer();
        customer.setDocumentNumber("12345678909");
        assertTrue(customer.documentNumberIsValid());
    }

    @Test
    void documentNumberIsValidWithInvalidCPF() {
        Customer customer = new Customer();
        customer.setDocumentNumber("12345678900");
        assertFalse(customer.documentNumberIsValid());
    }

    @Test
    void documentNumberIsValidWithRepeatedDigits() {
        Customer customer = new Customer();
        customer.setDocumentNumber("11111111111");
        assertFalse(customer.documentNumberIsValid());
    }

    @Test
    void documentNumberIsValidWithNullDocumentNumber() {
        Customer customer = new Customer();
        customer.setDocumentNumber(null);
        assertThrows(NullPointerException.class, customer::documentNumberIsValid);
    }

    @Test
    void documentNumberIsValidWithNonNumericCharacters() {
        Customer customer = new Customer();
        customer.setDocumentNumber("12345abc909");
        assertFalse(customer.documentNumberIsValid());
    }

    @Test
    void emailIsValidWithValidEmail() {
        Customer customer = new Customer();
        customer.setEmail("example@test.com");
        assertTrue(customer.emailIsValid());
    }

    @Test
    void emailIsValidWithInvalidEmail() {
        Customer customer = new Customer();
        customer.setEmail("example@test");
        assertFalse(customer.emailIsValid());
    }

    @Test
    void emailIsValidWithNullEmail() {
        Customer customer = new Customer();
        customer.setEmail(null);
        assertFalse(customer.emailIsValid());
    }

    @Test
    void emailIsValidWithEmptyEmail() {
        Customer customer = new Customer();
        customer.setEmail("");
        assertFalse(customer.emailIsValid());
    }

    @Test
    void emailIsValidWithSpecialCharacters() {
        Customer customer = new Customer();
        customer.setEmail("user+name@domain.co.uk");
        assertTrue(customer.emailIsValid());
    }
}