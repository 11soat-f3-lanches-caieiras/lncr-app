package br.com.tp.lanchescaieiras.kitchenorder.application.mappers;

import br.com.tp.lanchescaieiras.kitchenorder.adapters.outbound.entities.JpaKitchenOrderEntity;
import br.com.tp.lanchescaieiras.kitchenorder.domain.KitchenOrder;
import br.com.tp.lanchescaieiras.kitchenorder.domain.KitchenOrderStatus;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.*;

class KitchenOrderMapperTest {

    private final KitchenOrderMapper mapper = Mappers.getMapper(KitchenOrderMapper.class);

    @Test
    void testJpaToDomain() {
        JpaKitchenOrderEntity entity = new JpaKitchenOrderEntity();
        entity.setId(1);
        entity.setCustomerOrderId(2);
        entity.setStatusId(1);

        KitchenOrder order = mapper.jpatoDomain(entity);

        assertEquals(1, order.getId());
        assertEquals(2, order.getCustomerOrderId());
        assertEquals(KitchenOrderStatus.RECEIVED.getDescription(), order.getStatus());
    }

    @Test
    void testDomainToJpa() {
        KitchenOrder order = new KitchenOrder();
        order.setId(10);
        order.setCustomerOrderId(20);
        order.setStatus(KitchenOrderStatus.READY.getDescription());

        JpaKitchenOrderEntity entity = mapper.domainToJpa(order);

        assertEquals(10, entity.getId());
        assertEquals(20, entity.getCustomerOrderId());
        assertEquals(KitchenOrderStatus.READY.getId(), entity.getStatusId());
    }
}
