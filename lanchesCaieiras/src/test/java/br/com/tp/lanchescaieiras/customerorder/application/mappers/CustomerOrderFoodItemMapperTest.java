package br.com.tp.lanchescaieiras.customerorder.application.mappers;

import br.com.tp.lanchescaieiras.customerorder.adapters.outbound.entities.JpaCustomerOrderFoodItemEntity;
import br.com.tp.lanchescaieiras.customerorder.domain.CustomerOrderFoodItem;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class CustomerOrderFoodItemMapperTest {

    private final CustomerOrderFoodItemMapper mapper = new CustomerOrderFoodItemMapperImpl();

    @Test
    void testDomainToJpa() {
        CustomerOrderFoodItem item = new CustomerOrderFoodItem();
        item.setId(10);
        item.setPrice(5.5);
        item.setNotes("obs");
        JpaCustomerOrderFoodItemEntity entity = mapper.domainToJpa(item, 99);

        assertEquals(99, entity.getOrderId());
        assertEquals(10, entity.getFoodItemId());
        assertEquals(5.5, entity.getPrice());
        assertEquals("obs", entity.getNotes());
    }

    @Test
    void testJpaToDomain() {
        JpaCustomerOrderFoodItemEntity entity = new JpaCustomerOrderFoodItemEntity();
        entity.setFoodItemId(7);
        entity.setPrice(8.0);
        entity.setNotes("sem cebola");
        CustomerOrderFoodItem item = mapper.jpaToDomain(entity);

        assertEquals(7, item.getId());
        assertEquals(8.0, item.getPrice());
        assertEquals("sem cebola", item.getNotes());
        assertNull(item.getDescription());
        assertNull(item.getName());
    }
}

// Implementação manual para teste
class CustomerOrderFoodItemMapperImpl implements CustomerOrderFoodItemMapper {
    @Override
    public JpaCustomerOrderFoodItemEntity domainToJpa(CustomerOrderFoodItem customerOrderFoodItem, Integer customerOrderId) {
        JpaCustomerOrderFoodItemEntity entity = new JpaCustomerOrderFoodItemEntity();
        entity.setOrderId(customerOrderId);
        entity.setFoodItemId(customerOrderFoodItem.getId());
        entity.setPrice(customerOrderFoodItem.getPrice());
        entity.setNotes(customerOrderFoodItem.getNotes());
        return entity;
    }

    @Override
    public CustomerOrderFoodItem jpaToDomain(JpaCustomerOrderFoodItemEntity jpaCustomerOrderFoodItemEntity) {
        CustomerOrderFoodItem item = new CustomerOrderFoodItem();
        item.setId(jpaCustomerOrderFoodItemEntity.getFoodItemId());
        item.setPrice(jpaCustomerOrderFoodItemEntity.getPrice());
        item.setNotes(jpaCustomerOrderFoodItemEntity.getNotes());
        // name e description ignorados
        return item;
    }
}
