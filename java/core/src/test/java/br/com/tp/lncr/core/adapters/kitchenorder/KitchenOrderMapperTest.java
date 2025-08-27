package br.com.tp.lncr.core.adapters.kitchenorder;

import br.com.tp.lncr.core.commons.dtos.kitchenorder.KitchenOrderDTO;
import br.com.tp.lncr.core.commons.dtos.kitchenorder.KitchenOrderFoodItemDTO;
import br.com.tp.lncr.core.domain.kitchenorder.KitchenOrder;
import br.com.tp.lncr.core.domain.kitchenorder.KitchenOrderFoodItem;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class KitchenOrderMapperTest {
    @Test
    void testKitchenOrderToDTOAndBack() {
        KitchenOrderMapper mapper = new KitchenOrderMapper();
        KitchenOrder order = new KitchenOrder();
        order.setId(1);
        order.setCustomerOrderId(2);
        order.setStatus("RECEIVED");
        order.setFoodItems(Collections.emptyList());
        KitchenOrderDTO dto = mapper.kitchenOrderToDTO(order);
        assertNotNull(dto);
        assertEquals(order.getId(), dto.getId());
        assertEquals(order.getCustomerOrderId(), dto.getCustomerOrderId());
        assertEquals(order.getStatus(), dto.getStatus());
        KitchenOrder domain = mapper.kichenOrderToDomain(dto);
        assertNotNull(domain);
        assertEquals(dto.getId(), domain.getId());
    }

    @Test
    void testKitchenOrderFoodItemToDTOAndBack() {
        KitchenOrderMapper mapper = new KitchenOrderMapper();
        KitchenOrderFoodItem item = new KitchenOrderFoodItem();
        item.setId(1);
        item.setKitchenOrderId(2);
        item.setName("Burger");
        item.setDescription("Delicious");
        item.setNotes("No onions");
        KitchenOrderFoodItemDTO dto = mapper.kitchenOrderFoodItemToDTO(item);
        assertNotNull(dto);
        assertEquals(item.getId(), dto.getId());
        assertEquals(item.getKitchenOrderId(), dto.getKitchenOrderId());
        assertEquals(item.getName(), dto.getName());
        KitchenOrderFoodItem domain = mapper.kichenOrderFoodItemToDomain(dto);
        assertNotNull(domain);
        assertEquals(dto.getId(), domain.getId());
    }
}
