package br.com.tp.lncr.core.applications.customerorder;

import br.com.tp.lncr.core.commons.dtos.customerorder.CustomerOrderDTO;
import br.com.tp.lncr.core.commons.interfaces.customerorder.CustomerOrderGateway;
import br.com.tp.lncr.core.domain.customerorder.CustomerOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
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
    void deveCriarPedidoComSucesso() {
        CustomerOrderDTO dto = mock(CustomerOrderDTO.class);
        when(dto.getId()).thenReturn(1);
        when(dto.getStatus()).thenReturn(null);
        doNothing().when(dto).setStatus(anyString());
        CustomerOrder order = mock(CustomerOrder.class);
        when(gateway.createCustomerOrder(any())).thenReturn(order);
        doNothing().when(gateway).createPaymentCharge(any());
        doNothing().when(gateway).sendNotification(anyString(), anyInt(), anyString());

        CustomerOrder result = useCase.execute(dto);
        assertNotNull(result);
        verify(gateway).createCustomerOrder(any());
        verify(gateway).createPaymentCharge(any());
        verify(gateway).sendNotification(anyString(), anyInt(), contains("Aguardando pagamento"));
    }
}

