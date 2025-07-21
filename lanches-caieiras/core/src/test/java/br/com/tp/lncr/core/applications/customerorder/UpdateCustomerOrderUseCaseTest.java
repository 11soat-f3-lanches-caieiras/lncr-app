package br.com.tp.lncr.core.applications.customerorder;

import br.com.tp.lncr.core.commons.exceptions.CustomerOrderException;
import br.com.tp.lncr.core.commons.interfaces.customerorder.CustomerOrderGateway;
import br.com.tp.lncr.core.domain.customerorder.CustomerOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class UpdateCustomerOrderUseCaseTest {
    private CustomerOrderGateway gateway;
    private UpdateCustomerOrderUseCase useCase;

    @BeforeEach
    void setUp() {
        gateway = mock(CustomerOrderGateway.class);
        useCase = new UpdateCustomerOrderUseCase(gateway);
    }

    @Test
    void deveAtualizarStatusParaReceivedComSucesso() {
        CustomerOrder order = mock(CustomerOrder.class);
        when(gateway.getCustomerOrderById(anyInt())).thenReturn(order);
        when(order.getStatus()).thenReturn("RECEIVED");
        when(order.getId()).thenReturn(1);
        when(gateway.updateCustomerOrder(any())).thenReturn(order);
        doNothing().when(gateway).createKitchenOrder(any());
        CustomerOrder result = useCase.updateStatusById(1, "RECEIVED", false);
        assertNotNull(result);
        verify(gateway).updateCustomerOrder(any());
    }

    @Test
    void deveLancarExcecaoQuandoPedidoNaoEncontrado() {
        when(gateway.getCustomerOrderById(anyInt())).thenReturn(null);
        assertThrows(CustomerOrderException.class, () -> useCase.updateStatusById(1, "RECEIVED", false));
    }

    @Test
    void deveAtualizarStatusParaCancelledComSucesso() {
        CustomerOrder order = mock(CustomerOrder.class);
        when(gateway.getCustomerOrderById(anyInt())).thenReturn(order);
        when(order.getStatus()).thenReturn("CANCELLED");
        when(order.getId()).thenReturn(1);
        when(gateway.updateCustomerOrder(any())).thenReturn(order);
        doNothing().when(gateway).cancelPaymentChargeByCustomerOrderId(anyInt());
        when(order.getStatus()).thenReturn("CANCELLED");
        useCase.updateStatusById(1, "CANCELLED", false);
        verify(gateway).cancelPaymentChargeByCustomerOrderId(1);
    }
}

