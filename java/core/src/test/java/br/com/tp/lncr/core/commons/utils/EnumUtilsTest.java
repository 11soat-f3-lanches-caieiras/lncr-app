package br.com.tp.lncr.core.commons.utils;

import br.com.tp.lncr.core.commons.interfaces.EnumWithIdDescription;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EnumUtilsTest {
    enum TestEnum implements EnumWithIdDescription {
        UM(1, "Primeiro"), DOIS(2, "Segundo"), TRES(3, "Terceiro");
        private final int id;
        private final String description;
        TestEnum(int id, String description) { this.id = id; this.description = description; }
        public Integer getId() { return id; }
        public String getDescription() { return description; }
    }

    @Test
    void testFromId() {
        assertEquals(TestEnum.UM, EnumUtils.fromId(TestEnum.class, 1, new RuntimeException()));
        assertThrows(RuntimeException.class, () -> EnumUtils.fromId(TestEnum.class, 99, new RuntimeException()));
    }

    @Test
    void testFromDescription() {
        assertEquals(TestEnum.DOIS, EnumUtils.fromDescription(TestEnum.class, "Segundo", new RuntimeException()));
        assertThrows(RuntimeException.class, () -> EnumUtils.fromDescription(TestEnum.class, "Inexistente", new RuntimeException()));
    }

    @Test
    void testListOfAllowIds() {
        assertEquals("1,2,3", EnumUtils.listOfAllowIds(TestEnum.class));
    }

    @Test
    void testListOfAllowDescriptions() {
        assertEquals("Primeiro, Segundo, Terceiro", EnumUtils.listOfAllowDescriptions(TestEnum.class));
    }

    @Test
    void testValidateNewStatusRules() {
        assertEquals("Segundo", EnumUtils.validateNewStatusRules(TestEnum.class, "Primeiro", "Segundo", false, RuntimeException::new));
        assertEquals("Segundo", EnumUtils.validateNewStatusRules(TestEnum.class, "Segundo", "Segundo", false, RuntimeException::new));
        assertEquals("Terceiro", EnumUtils.validateNewStatusRules(TestEnum.class, "Segundo", "Terceiro", true, RuntimeException::new));
        assertThrows(RuntimeException.class, () -> EnumUtils.validateNewStatusRules(TestEnum.class, "Primeiro", "Terceiro", false, RuntimeException::new));
    }
}

