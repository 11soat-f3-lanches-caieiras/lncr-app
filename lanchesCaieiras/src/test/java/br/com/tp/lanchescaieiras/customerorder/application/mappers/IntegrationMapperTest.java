package br.com.tp.lanchescaieiras.customerorder.application.mappers;

import br.com.tp.lanchescaieiras.customerorder.domain.CustomerOrderFoodItem;
import br.com.tp.lanchescaieiras.kitchenorder.domain.KitchenOrderFoodItem;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IntegrationMapperTest {

    private final IntegrationMapper mapper = new IntegrationMapperImpl();

    @Test
    void testOrderToKitchen() {
        CustomerOrderFoodItem item = new CustomerOrderFoodItem();
        item.setId(1);
        item.setName("Coxinha");
        item.setDescription("Frango");
        item.setNotes("Sem pimenta");

        KitchenOrderFoodItem kitchenItem = mapper.orderToKichen(item);

        assertEquals(1, kitchenItem.getId());
        assertEquals("Coxinha", kitchenItem.getName());
        assertEquals("Frango", kitchenItem.getDescription());
        assertEquals("Sem pimenta", kitchenItem.getNotes());
    }
}

// Implementação manual para teste
class IntegrationMapperImpl implements IntegrationMapper {
    @Override
    public KitchenOrderFoodItem orderToKichen(CustomerOrderFoodItem customerOrderFoodItem) {
        KitchenOrderFoodItem item = new KitchenOrderFoodItem();
        item.setId(customerOrderFoodItem.getId());
        item.setName(customerOrderFoodItem.getName());
        item.setDescription(customerOrderFoodItem.getDescription());
        item.setNotes(customerOrderFoodItem.getNotes());
        return item;
    }
}
