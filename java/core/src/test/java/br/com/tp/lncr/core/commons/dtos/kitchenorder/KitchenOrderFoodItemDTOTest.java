package br.com.tp.lncr.core.commons.dtos.kitchenorder;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class KitchenOrderFoodItemDTOTest {
    @Test
    void createKitchenOrderFoodItemDTOWithAllFields() {
        KitchenOrderFoodItemDTO dto = new KitchenOrderFoodItemDTO(1, 2, "Pizza", "Mussarela", "Sem cebola");
        Assertions.assertEquals(1, dto.getId());
        Assertions.assertEquals(2, dto.getKitchenOrderId());
        Assertions.assertEquals("Pizza", dto.getName());
        Assertions.assertEquals("Mussarela", dto.getDescription());
        Assertions.assertEquals("Sem cebola", dto.getNotes());
    }

    @Test
    void setAndGetFieldsIndividually() {
        KitchenOrderFoodItemDTO dto = new KitchenOrderFoodItemDTO();
        dto.setId(3);
        dto.setKitchenOrderId(4);
        dto.setName("Hamburguer");
        dto.setDescription("Carne");
        dto.setNotes("Sem sal");
        Assertions.assertEquals(3, dto.getId());
        Assertions.assertEquals(4, dto.getKitchenOrderId());
        Assertions.assertEquals("Hamburguer", dto.getName());
        Assertions.assertEquals("Carne", dto.getDescription());
        Assertions.assertEquals("Sem sal", dto.getNotes());
    }

    @Test
    void allowNullFields() {
        KitchenOrderFoodItemDTO dto = new KitchenOrderFoodItemDTO(null, null, null, null, null);
        Assertions.assertNull(dto.getId());
        Assertions.assertNull(dto.getKitchenOrderId());
        Assertions.assertNull(dto.getName());
        Assertions.assertNull(dto.getDescription());
        Assertions.assertNull(dto.getNotes());
    }

    @Test
    void toStringReturnsExpectedFormat() {
        KitchenOrderFoodItemDTO dto = new KitchenOrderFoodItemDTO(1, 2, "Pizza", "Mussarela", "Sem cebola");
        String str = dto.toString();
        Assertions.assertTrue(str.contains("id=1"));
        Assertions.assertTrue(str.contains("kitchenOrderId=2"));
        Assertions.assertTrue(str.contains("name='Pizza'"));
        Assertions.assertTrue(str.contains("description='Mussarela'"));
        Assertions.assertTrue(str.contains("notes='Sem cebola'"));
    }
}

