package br.com.tp.lncr.app.datasources.postgres.customer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class JpaCustomerEntityTest {

    @Test
    void shouldCreateEntityWithAllParameters() {
        Integer id = 1;
        String documentNumber = "12345678901";
        String name = "João Silva";
        String email = "joao@email.com";

        JpaCustomerEntity entity = new JpaCustomerEntity(id, documentNumber, name, email);

        assertEquals(id, entity.getId());
        assertEquals(documentNumber, entity.getDocumentNumber());
        assertEquals(name, entity.getName());
        assertEquals(email, entity.getEmail());
    }

    @Test
    void shouldCreateEmptyEntity() {
        JpaCustomerEntity entity = new JpaCustomerEntity();

        assertNull(entity.getId());
        assertNull(entity.getDocumentNumber());
        assertNull(entity.getName());
        assertNull(entity.getEmail());
    }

    @Test
    void shouldSetAndGetId() {
        JpaCustomerEntity entity = new JpaCustomerEntity();
        Integer id = 123;

        entity.setId(id);

        assertEquals(id, entity.getId());
    }

    @Test
    void shouldSetAndGetDocumentNumber() {
        JpaCustomerEntity entity = new JpaCustomerEntity();
        String documentNumber = "98765432100";

        entity.setDocumentNumber(documentNumber);

        assertEquals(documentNumber, entity.getDocumentNumber());
    }

    @Test
    void shouldSetAndGetName() {
        JpaCustomerEntity entity = new JpaCustomerEntity();
        String name = "Maria Santos";

        entity.setName(name);

        assertEquals(name, entity.getName());
    }

    @Test
    void shouldSetAndGetEmail() {
        JpaCustomerEntity entity = new JpaCustomerEntity();
        String email = "maria@email.com";

        entity.setEmail(email);

        assertEquals(email, entity.getEmail());
    }

    @Test
    void shouldHandleNullValues() {
        JpaCustomerEntity entity = new JpaCustomerEntity(null, null, null, null);

        assertNull(entity.getId());
        assertNull(entity.getDocumentNumber());
        assertNull(entity.getName());
        assertNull(entity.getEmail());
    }

    @Test
    void shouldHandleEmptyStrings() {
        JpaCustomerEntity entity = new JpaCustomerEntity(1, "", "", "");

        assertEquals(1, entity.getId());
        assertEquals("", entity.getDocumentNumber());
        assertEquals("", entity.getName());
        assertEquals("", entity.getEmail());
    }
}
