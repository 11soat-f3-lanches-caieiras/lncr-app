package br.com.tp.lncr.core.applications.kitchenorder;

import br.com.tp.lncr.core.commons.enums.KitchenOrderStatus;
import br.com.tp.lncr.core.commons.exceptions.KitchenOrderException;
import br.com.tp.lncr.core.commons.interfaces.kitchenorder.KitchenOrderGateway;
import br.com.tp.lncr.core.domain.kitchenorder.KitchenOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class UpdateKitchenOrderUseCaseTest {
    private KitchenOrderGateway kitchenOrderGateway;
    private UpdateKitchenOrderUseCase useCase;

    @BeforeEach
    void setUp() {
        kitchenOrderGateway = mock(KitchenOrderGateway.class);
        useCase = new UpdateKitchenOrderUseCase(kitchenOrderGateway);
    }

    @Test
    void deveAtualizarStatusComSucesso() {
        KitchenOrder order = mock(KitchenOrder.class);
        when(kitchenOrderGateway.getKitchenOrderById(1)).thenReturn(order);
        when(order.getStatus()).thenReturn(KitchenOrderStatus.PREPARING.getDescription());
        when(order.getCustomerOrderId()).thenReturn(100);
        when(order.getId()).thenReturn(1);
        when(kitchenOrderGateway.saveKitchenOrder(order)).thenReturn(order);
        KitchenOrder result = useCase.updateStatus(1, KitchenOrderStatus.PREPARING.getDescription(), false, true);
        assertEquals(order, result);
        verify(kitchenOrderGateway).updateCustomerOrderStatus(100, KitchenOrderStatus.PREPARING.getDescription());
        verify(kitchenOrderGateway).sendNotification(anyString(), anyInt(), anyString());
    }

    @Test
    void deveLancarExcecaoQuandoNaoEncontrarPedido() {
        when(kitchenOrderGateway.getKitchenOrderById(2)).thenReturn(null);
        assertThrows(KitchenOrderException.class, () -> useCase.updateStatus(2, "READY", false, false));
    }

    @Test
    void naoDeveAtualizarPedidoClienteQuandoUpdateCustomerOrderFalse() {
        KitchenOrder order = mock(KitchenOrder.class);
        when(kitchenOrderGateway.getKitchenOrderById(3)).thenReturn(order);
        when(order.getStatus()).thenReturn(KitchenOrderStatus.READY.getDescription());
        when(order.getCustomerOrderId()).thenReturn(200);
        when(order.getId()).thenReturn(3);
        when(kitchenOrderGateway.saveKitchenOrder(order)).thenReturn(order);
        useCase.updateStatus(3, KitchenOrderStatus.READY.getDescription(), false, false);
        verify(kitchenOrderGateway, never()).updateCustomerOrderStatus(anyInt(), anyString());
        verify(kitchenOrderGateway).sendNotification(anyString(), anyInt(), anyString());
    }
}

