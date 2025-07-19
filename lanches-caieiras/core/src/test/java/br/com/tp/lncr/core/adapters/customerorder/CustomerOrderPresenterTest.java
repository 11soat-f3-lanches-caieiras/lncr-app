package br.com.tp.lncr.core.adapters.customerorder;

import br.com.tp.lncr.core.commons.dtos.customerorder.CustomerOrderDTO;
import br.com.tp.lncr.core.domain.customerorder.CustomerOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class CustomerOrderPresenterTest {
    private CustomerOrderMapper mapper;
    private CustomerOrderPresenter presenter;

    @BeforeEach
    void setUp() {
        mapper = mock(CustomerOrderMapper.class);
        presenter = new CustomerOrderPresenter(mapper);
    }

    @Test
    void testCreated() {
        CustomerOrder order = new CustomerOrder();
        CustomerOrderDTO dto = new CustomerOrderDTO();
        when(mapper.customerOrderToDTO(order)).thenReturn(dto);
        CustomerOrderDTO result = presenter.created(order);
        assertNotNull(result);
        verify(mapper).customerOrderToDTO(order);
    }

    @Test
    void testGetById() {
        CustomerOrder order = new CustomerOrder();
        CustomerOrderDTO dto = new CustomerOrderDTO();
        when(mapper.customerOrderToDTO(order)).thenReturn(dto);
        CustomerOrderDTO result = presenter.getById(order);
        assertNotNull(result);
        verify(mapper).customerOrderToDTO(order);
    }

    @Test
    void testGetByStatusList() {
        CustomerOrder order = new CustomerOrder();
        CustomerOrderDTO dto = new CustomerOrderDTO();
        List<CustomerOrder> orderList = List.of(order);
        List<String> statusList = List.of("PENDING");
        // Simula o utilitário de ordenação
        CustomerOrderPresenter realPresenter = new CustomerOrderPresenter(new CustomerOrderMapper());
        List<CustomerOrderDTO> result = realPresenter.getByStatusList(orderList, statusList);
        assertNotNull(result);
    }

    @Test
    void testUpdatedStatus() {
        CustomerOrder order = new CustomerOrder();
        CustomerOrderDTO dto = new CustomerOrderDTO();
        when(mapper.customerOrderToDTO(order)).thenReturn(dto);
        CustomerOrderDTO result = presenter.updatedStatus(order);
        assertNotNull(result);
        verify(mapper).customerOrderToDTO(order);
    }
}

