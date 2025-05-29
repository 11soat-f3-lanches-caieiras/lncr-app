package br.com.tp.lanchescaieiras.customer.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    @Test
    void testConstructorAndGetters() {
        Customer customer = new Customer(1, "12345678909", "João", "joao@email.com");
        assertEquals(1, customer.getId());
        assertEquals("12345678909", customer.getDocumentNumber());
        assertEquals("João", customer.getName());
        assertEquals("joao@email.com", customer.getEmail());
    }

    @Test
    void testSetters() {
        Customer customer = new Customer();
        customer.setId(2);
        customer.setDocumentNumber("98765432100");
        customer.setName("Maria");
        customer.setEmail("maria@email.com");
        assertEquals(2, customer.getId());
        assertEquals("98765432100", customer.getDocumentNumber());
        assertEquals("Maria", customer.getName());
        assertEquals("maria@email.com", customer.getEmail());
    }

    @Test
    void testDocumentNumberIsValid_ValidCPF() {
        Customer customer = new Customer();
        customer.setDocumentNumber("52998224725"); // CPF válido
        assertTrue(customer.documentNumberIsValid());
    }

    @Test
    void testDocumentNumberIsValid_InvalidCPF() {
        Customer customer = new Customer();
        customer.setDocumentNumber("11111111111"); // CPF inválido (todos iguais)
        assertFalse(customer.documentNumberIsValid());
        customer.setDocumentNumber("12345678900"); // CPF inválido
        assertFalse(customer.documentNumberIsValid());
        customer.setDocumentNumber("abc"); // CPF inválido (não numérico)
        assertFalse(customer.documentNumberIsValid());
    }

    @Test
    void testEmailIsValid() {
        Customer customer = new Customer();
        customer.setEmail("teste@dominio.com");
        assertTrue(customer.emailIsValid());
        customer.setEmail("invalido@dominio");
        assertFalse(customer.emailIsValid());
        customer.setEmail("invalido.com");
        assertFalse(customer.emailIsValid());
        customer.setEmail(null);
        assertFalse(customer.emailIsValid());
    }

    @Test
    void testToString() {
        Customer customer = new Customer(1, "12345678909", "João", "joao@email.com");
        String str = customer.toString();
        assertTrue(str.contains("Customer{"));
        assertTrue(str.contains("id=1"));
        assertTrue(str.contains("documentNumber='12345678909'"));
        assertTrue(str.contains("name='João'"));
        assertTrue(str.contains("email='joao@email.com'"));
    }
}
