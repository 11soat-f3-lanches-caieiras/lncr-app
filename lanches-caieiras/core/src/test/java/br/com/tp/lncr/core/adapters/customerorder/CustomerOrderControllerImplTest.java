package br.com.tp.lncr.core.adapters.customerorder;

import br.com.tp.lncr.core.commons.dtos.customerorder.CustomerOrderDTO;
import br.com.tp.lncr.core.commons.interfaces.customerorder.CustomerOrderDatabase;
import br.com.tp.lncr.core.domain.customerorder.CustomerOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class CustomerOrderControllerImplTest {
    private CustomerOrderDatabase customerOrderDatabase;
    private CustomerOrderControllerImpl controller;
    private CustomerOrderDTO customerOrderDTO;
    private CustomerOrder customerOrder;

    @BeforeEach
    void setUp() {
        customerOrderDatabase = mock(CustomerOrderDatabase.class);
        controller = new CustomerOrderControllerImpl(customerOrderDatabase);
        customerOrderDTO = new CustomerOrderDTO();
        customerOrder = new CustomerOrder();
    }

    @Test
    void testCreate() {
        CustomerOrderControllerImpl spyController = Mockito.spy(controller);
        doReturn(customerOrder).when(spyController).create(any(), any());
        CustomerOrderDTO result = controller.create(customerOrderDatabase, customerOrderDTO);
        assertNotNull(result);
    }

    @Test
    void testGetById() {
        CustomerOrderControllerImpl spyController = Mockito.spy(controller);
        doReturn(customerOrder).when(spyController).getById(any(), anyInt(), anyBoolean());
        CustomerOrderDTO result = controller.getById(customerOrderDatabase, 1, true);
        assertNotNull(result);
    }

    @Test
    void testGetByStatusList() {
        List<String> statusList = List.of("PENDING", "DONE");
        CustomerOrderControllerImpl spyController = Mockito.spy(controller);
        doReturn(List.of(customerOrder)).when(spyController).getByStatusList(any(), anyList(), anyBoolean());
        List<CustomerOrderDTO> result = controller.getByStatusList(customerOrderDatabase, statusList, true);
        assertNotNull(result);
    }

    @Test
    void testUpdateStatusById() {
        CustomerOrderControllerImpl spyController = Mockito.spy(controller);
        doReturn(customerOrder).when(spyController).updateStatusById(any(), anyInt(), anyString(), anyBoolean());
        CustomerOrderDTO result = controller.updateStatusById(customerOrderDatabase, 1, "DONE", true);
        assertNotNull(result);
    }
}

