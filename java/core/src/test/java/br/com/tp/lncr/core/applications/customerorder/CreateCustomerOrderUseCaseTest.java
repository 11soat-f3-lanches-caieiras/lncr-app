package br.com.tp.lncr.core.applications.customerorder;

import br.com.tp.lncr.core.commons.dtos.customerorder.CustomerOrderCustomerDTO;
import br.com.tp.lncr.core.commons.dtos.customerorder.CustomerOrderDTO;
import br.com.tp.lncr.core.commons.dtos.customerorder.CustomerOrderFoodItemDTO;
import br.com.tp.lncr.core.commons.enums.CustomerOrderStatus;
import br.com.tp.lncr.core.commons.interfaces.customerorder.CustomerOrderGateway;
import br.com.tp.lncr.core.domain.customerorder.CustomerOrder;
import br.com.tp.lncr.core.domain.customerorder.CustomerOrderCustomer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class CreateCustomerOrderUseCaseTest {
    private CustomerOrderGateway gateway;
    private CreateCustomerOrderUseCase useCase;

    @BeforeEach
    void setUp() {
        gateway = mock(CustomerOrderGateway.class);
        useCase = new CreateCustomerOrderUseCase(gateway);
    }

    @Test
    void createsOrderSuccessfully() {
        CustomerOrderDTO dto = mock(CustomerOrderDTO.class);
        when(dto.getId()).thenReturn(10);

        // Usar getCustomer() ao invés de getCustomerId()
        CustomerOrderCustomerDTO customerDTO = mock(CustomerOrderCustomerDTO.class);
        when(customerDTO.getId()).thenReturn(1);
        when(dto.getCustomer()).thenReturn(customerDTO);

        // Mock para getCustomerDetails retornar CustomerOrderCustomer (objeto de domínio)
        CustomerOrderCustomer customerDetails = mock(CustomerOrderCustomer.class);
        when(customerDetails.getId()).thenReturn(1);
        when(customerDetails.getName()).thenReturn("Tito");
        when(gateway.getCustomerDetails(1)).thenReturn(customerDetails);

        // Adiciona pelo menos um item ao pedido
        CustomerOrderFoodItemDTO foodItemDTO = mock(CustomerOrderFoodItemDTO.class);
        when(dto.getFoodItems()).thenReturn(List.of(foodItemDTO));

        CustomerOrder order = mock(CustomerOrder.class);
        when(gateway.createCustomerOrder(any())).thenReturn(order);
        doNothing().when(gateway).createPaymentCharge(any());
        doNothing().when(gateway).sendNotification(anyString(), anyInt(), anyString());

        CustomerOrder result = useCase.execute(dto);
        assertNotNull(result);
        verify(dto).setStatus(CustomerOrderStatus.CHECKOUT.getDescription());
        verify(gateway).createCustomerOrder(any());
        verify(gateway).createPaymentCharge(any());
        verify(gateway).sendNotification(eq("CUSTOMER_ORDER_CHECKOUT"), anyInt(), contains("Aguardando pagamento"));
    }

    @Test
    void propagatesExceptionIfGatewayFails() {
        CustomerOrderDTO dto = mock(CustomerOrderDTO.class);
        when(dto.getId()).thenReturn(11);

        // Usar getCustomer() ao invés de getCustomerId()
        CustomerOrderCustomerDTO customerDTO = mock(CustomerOrderCustomerDTO.class);
        when(customerDTO.getId()).thenReturn(1);
        when(dto.getCustomer()).thenReturn(customerDTO);

        // Mock para getCustomerDetails retornar CustomerOrderCustomer (objeto de domínio)
        CustomerOrderCustomer customerDetails = mock(CustomerOrderCustomer.class);
        when(customerDetails.getId()).thenReturn(1);
        when(customerDetails.getName()).thenReturn("Tito");
        when(gateway.getCustomerDetails(1)).thenReturn(customerDetails);

        // Adiciona pelo menos um item para passar na validação do construtor
        CustomerOrderFoodItemDTO foodItemDTO = mock(CustomerOrderFoodItemDTO.class);
        when(dto.getFoodItems()).thenReturn(List.of(foodItemDTO));

        when(gateway.createCustomerOrder(any())).thenThrow(new RuntimeException("DB error"));
        assertThrows(RuntimeException.class, () -> useCase.execute(dto));
    }

    @Test
    void setsStatusToCheckoutEvenIfAlreadySet() {
        CustomerOrderDTO dto = mock(CustomerOrderDTO.class);
        when(dto.getId()).thenReturn(12);
        when(dto.getStatus()).thenReturn(CustomerOrderStatus.CHECKOUT.getDescription());

        // Usar getCustomer() ao invés de getCustomerId()
        CustomerOrderCustomerDTO customerDTO = mock(CustomerOrderCustomerDTO.class);
        when(customerDTO.getId()).thenReturn(1);
        when(dto.getCustomer()).thenReturn(customerDTO);

        // Mock para getCustomerDetails retornar CustomerOrderCustomer (objeto de domínio)
        CustomerOrderCustomer customerDetails = mock(CustomerOrderCustomer.class);
        when(customerDetails.getId()).thenReturn(1);
        when(customerDetails.getName()).thenReturn("Tito");
        when(gateway.getCustomerDetails(1)).thenReturn(customerDetails);

        // Adiciona pelo menos um item para passar na validação do construtor
        CustomerOrderFoodItemDTO foodItemDTO = mock(CustomerOrderFoodItemDTO.class);
        when(dto.getFoodItems()).thenReturn(List.of(foodItemDTO));

        CustomerOrder order = mock(CustomerOrder.class);
        when(gateway.createCustomerOrder(any())).thenReturn(order);
        doNothing().when(gateway).createPaymentCharge(any());
        doNothing().when(gateway).sendNotification(anyString(), anyInt(), anyString());

        useCase.execute(dto);
        verify(dto, atLeastOnce()).setStatus(CustomerOrderStatus.CHECKOUT.getDescription());
    }
}
