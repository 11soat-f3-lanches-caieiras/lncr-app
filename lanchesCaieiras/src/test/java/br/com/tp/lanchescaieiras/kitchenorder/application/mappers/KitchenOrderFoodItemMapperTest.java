package br.com.tp.lanchescaieiras.kitchenorder.application.mappers;

import br.com.tp.lanchescaieiras.kitchenorder.adapters.outbound.entities.JpaKitchenOrderFoodItemEntity;
import br.com.tp.lanchescaieiras.kitchenorder.domain.KitchenOrderFoodItem;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.*;

class KitchenOrderFoodItemMapperTest {

    private final KitchenOrderFoodItemMapper mapper = Mappers.getMapper(KitchenOrderFoodItemMapper.class);

    @Test
    void testDomainToJpa() {
        KitchenOrderFoodItem domain = new KitchenOrderFoodItem();
        domain.setName("Coxinha");
        domain.setDescription("Frango");
        domain.setNotes("Sem pimenta");

        JpaKitchenOrderFoodItemEntity entity = mapper.domainToJpa(domain, 123);

        assertNull(entity.getId());
        assertEquals(123, entity.getKitchenOrderId());
        assertEquals("Coxinha", entity.getName());
        assertEquals("Frango", entity.getDescription());
        assertEquals("Sem pimenta", entity.getNotes());
    }

    @Test
    void testJpaToDomain() {
        JpaKitchenOrderFoodItemEntity entity = new JpaKitchenOrderFoodItemEntity();
        entity.setId(10);
        entity.setName("Pastel");
        entity.setDescription("Carne");
        entity.setNotes("Bem passado");

        KitchenOrderFoodItem domain = mapper.jpaToDomain(entity);

        assertEquals(10, domain.getId());
        assertEquals("Pastel", domain.getName());
        assertEquals("Carne", domain.getDescription());
        assertEquals("Bem passado", domain.getNotes());
    }
}
