package br.com.tp.lncr.app.apis;

import br.com.tp.lncr.app.apis.customerorder.CustomerOrderRestControllerImpl;
import br.com.tp.lncr.app.commons.model.ResponseListModel;
import br.com.tp.lncr.app.commons.model.ResponseModel;
import br.com.tp.lncr.app.configs.CustomerOrderConfig;
import br.com.tp.lncr.app.dataproxy.CustomerOrderDataProxy;
import br.com.tp.lncr.core.commons.dtos.customerorder.CustomerOrderCustomerDTO;
import br.com.tp.lncr.core.commons.dtos.customerorder.CustomerOrderDTO;
import br.com.tp.lncr.core.commons.interfaces.customerorder.CustomerOrderController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerOrderRestControllerImplTest {

    @Mock
    private CustomerOrderConfig customerOrderConfig;

    @Mock
    private CustomerOrderDataProxy customerOrderDatabase;

    @Mock
    private CustomerOrderController customerOrderController;

    @InjectMocks
    private CustomerOrderRestControllerImpl customerOrderRestController;

    private CustomerOrderDTO customerOrderDTO;

    @BeforeEach
    void setUp() {
        customerOrderDTO = new CustomerOrderDTO();
        customerOrderDTO.setId(1);
        customerOrderDTO.setCustomer(new CustomerOrderCustomerDTO());
        customerOrderDTO.getCustomer().setId(1);
        customerOrderDTO.setStatus("PENDING");
    }

    @Test
    void deveCriarCustomerOrderComSucesso() {
        when(customerOrderController.create(customerOrderDatabase, customerOrderDTO)).thenReturn(customerOrderDTO);
        when(customerOrderConfig.getLocationPrefix()).thenReturn("/customerOrders");

        ResponseEntity<ResponseModel<CustomerOrderDTO>> response = customerOrderRestController.createCustomerOrder(customerOrderDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(customerOrderDTO, response.getBody().get_content());
        assertNotNull(response.getHeaders().getLocation());
        assertTrue(response.getHeaders().getLocation().toString().contains("/customerOrders/1"));
        verify(customerOrderController).create(customerOrderDatabase, customerOrderDTO);
    }

    @Test
    void deveRetornarCustomerOrderPorIdSemFoodItems() {
        when(customerOrderController.getById(customerOrderDatabase, 1, false)).thenReturn(customerOrderDTO);

        ResponseEntity<ResponseModel<CustomerOrderDTO>> response = customerOrderRestController.getCustomerOrderById(1, false);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(customerOrderDTO, response.getBody().get_content());
        verify(customerOrderController).getById(customerOrderDatabase, 1, false);
    }

    @Test
    void deveRetornarCustomerOrderPorIdComFoodItems() {
        when(customerOrderController.getById(customerOrderDatabase, 1, true)).thenReturn(customerOrderDTO);

        ResponseEntity<ResponseModel<CustomerOrderDTO>> response = customerOrderRestController.getCustomerOrderById(1, true);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(customerOrderDTO, response.getBody().get_content());
        verify(customerOrderController).getById(customerOrderDatabase, 1, true);
    }

    @Test
    void deveRetornarCustomerOrdersPorStatusListSemFoodItems() {
        List<String> statusList = List.of("PENDING", "CONFIRMED");
        List<CustomerOrderDTO> orders = List.of(customerOrderDTO);
        when(customerOrderController.getByStatusList(customerOrderDatabase, statusList, false)).thenReturn(orders);

        ResponseEntity<ResponseListModel<CustomerOrderDTO>> response = customerOrderRestController.getCustomerOrderByStatus(statusList, false);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(orders, response.getBody().get_content());
        verify(customerOrderController).getByStatusList(customerOrderDatabase, statusList, false);
    }

    @Test
    void deveRetornarCustomerOrdersPorStatusListComFoodItems() {
        List<String> statusList = List.of("PENDING");
        List<CustomerOrderDTO> orders = List.of(customerOrderDTO);
        when(customerOrderController.getByStatusList(customerOrderDatabase, statusList, true)).thenReturn(orders);

        ResponseEntity<ResponseListModel<CustomerOrderDTO>> response = customerOrderRestController.getCustomerOrderByStatus(statusList, true);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(orders, response.getBody().get_content());
        verify(customerOrderController).getByStatusList(customerOrderDatabase, statusList, true);
    }

    @Test
    void deveAtualizarStatusDoCustomerOrderSemForceUpdate() {
        CustomerOrderDTO updatedOrder = new CustomerOrderDTO();
        updatedOrder.setId(1);
        updatedOrder.setStatus("CONFIRMED");
        when(customerOrderController.updateStatusById(customerOrderDatabase, 1, "CONFIRMED", false)).thenReturn(updatedOrder);

        ResponseEntity<ResponseModel<CustomerOrderDTO>> response = customerOrderRestController.updateOrderStatusById(1, "CONFIRMED", false);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(updatedOrder, response.getBody().get_content());
        verify(customerOrderController).updateStatusById(customerOrderDatabase, 1, "CONFIRMED", false);
    }

    @Test
    void deveAtualizarStatusDoCustomerOrderComForceUpdate() {
        CustomerOrderDTO updatedOrder = new CustomerOrderDTO();
        updatedOrder.setId(1);
        updatedOrder.setStatus("CANCELLED");
        when(customerOrderController.updateStatusById(customerOrderDatabase, 1, "CANCELLED", true)).thenReturn(updatedOrder);

        ResponseEntity<ResponseModel<CustomerOrderDTO>> response = customerOrderRestController.updateOrderStatusById(1, "CANCELLED", true);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(updatedOrder, response.getBody().get_content());
        verify(customerOrderController).updateStatusById(customerOrderDatabase, 1, "CANCELLED", true);
    }

    @Test
    void deveRetornarListaVaziaQuandoNaoHouverOrdersComStatus() {
        List<String> statusList = List.of("NONEXISTENT");
        List<CustomerOrderDTO> emptyOrders = List.of();
        when(customerOrderController.getByStatusList(customerOrderDatabase, statusList, false)).thenReturn(emptyOrders);

        ResponseEntity<ResponseListModel<CustomerOrderDTO>> response = customerOrderRestController.getCustomerOrderByStatus(statusList, false);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().get_content().isEmpty());
        verify(customerOrderController).getByStatusList(customerOrderDatabase, statusList, false);
    }
}
