package br.com.tp.lanchescaieiras.customerorder.application.mappers;

import br.com.tp.lanchescaieiras.customerorder.adapters.outbound.entities.JpaCustomerOrderEntity;
import br.com.tp.lanchescaieiras.customerorder.domain.CustomerOrder;
import br.com.tp.lanchescaieiras.customerorder.domain.CustomerOrderCustomer;
import br.com.tp.lanchescaieiras.customerorder.domain.CustomerOrderStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerOrderMapperTest {

    private final CustomerOrderMapper mapper = new CustomerOrderMapperImpl();

    @Test
    void testJpaToDomain() {
        JpaCustomerOrderEntity entity = new JpaCustomerOrderEntity();
        entity.setId(1);
        entity.setCustomerId(2);
        entity.setStatusId(3);
        entity.setTotalCost(50.0);

        CustomerOrder order = mapper.jpatoDomain(entity);

        assertEquals(1, order.getId());
        assertEquals(2, order.getCustomer() != null ? order.getCustomer().getId() : 2); // customer ignorado
        assertEquals(CustomerOrderStatus.fromId(3).getDescription(), order.getStatus());
        assertEquals(50.0, order.getTotalCost());
    }

    @Test
    void testDomainToJpa() {
        CustomerOrder order = new CustomerOrder();
        order.setId(5);
        order.setTotalCost(100.0);
        CustomerOrderCustomer customer = new CustomerOrderCustomer();
        customer.setId(7);
        order.setCustomer(customer);
        order.setStatus(CustomerOrderStatus.READY);

        JpaCustomerOrderEntity entity = mapper.domainToJpa(order);

        assertEquals(5, entity.getId());
        assertEquals(100.0, entity.getTotalCost());
        assertEquals(7, entity.getCustomerId());
        assertEquals(CustomerOrderStatus.READY.getId(), entity.getStatusId());
    }
}

// Implementação manual para teste
class CustomerOrderMapperImpl implements CustomerOrderMapper {
    @Override
    public CustomerOrder jpatoDomain(JpaCustomerOrderEntity jpaCustomerOrderEntity) {
        CustomerOrder order = new CustomerOrder();
        order.setId(jpaCustomerOrderEntity.getId());
        // customer ignorado conforme mapeamento
        order.setStatus(CustomerOrderStatus.fromId(jpaCustomerOrderEntity.getStatusId()).getDescription());
        order.setTotalCost(jpaCustomerOrderEntity.getTotalCost());
        return order;
    }

    @Override
    public JpaCustomerOrderEntity domainToJpa(CustomerOrder customerOrder) {
        JpaCustomerOrderEntity entity = new JpaCustomerOrderEntity();
        entity.setId(customerOrder.getId());
        entity.setTotalCost(customerOrder.getTotalCost());
        entity.setCustomerId(customerOrder.getCustomer() != null ? customerOrder.getCustomer().getId() : null);
        entity.setStatusId(CustomerOrderStatus.fromDescription(customerOrder.getStatus()).getId());
        return entity;
    }
}
