package br.com.tp.lncr.core.applications.customerorder;

import br.com.tp.lncr.core.commons.enums.CustomerOrderStatus;
import br.com.tp.lncr.core.commons.exceptions.CustomerOrderException;
import br.com.tp.lncr.core.commons.interfaces.customerorder.CustomerOrderGateway;
import br.com.tp.lncr.core.domain.customerorder.CustomerOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GetCustomerOrderUseCaseTest {
    private CustomerOrderGateway gateway;
    private GetCustomerOrderUseCase useCase;

    @BeforeEach
    void setUp() {
        gateway = mock(CustomerOrderGateway.class);
        useCase = new GetCustomerOrderUseCase(gateway);
    }

    @Test
    void deveBuscarPedidoPorIdComSucesso() {
        CustomerOrder order = mock(CustomerOrder.class);
        when(gateway.getCustomerOrderById(anyInt(), anyBoolean())).thenReturn(order);
        when(order.toString()).thenReturn("Pedido");
        CustomerOrder result = useCase.getById(1, true);
        assertNotNull(result);
        verify(gateway).getCustomerOrderById(1, true);
    }

    @Test
    void deveLancarExcecaoQuandoPedidoNaoEncontrado() {
        when(gateway.getCustomerOrderById(anyInt(), anyBoolean())).thenReturn(null);
        assertThrows(CustomerOrderException.class, () -> useCase.getById(1, true));
    }

    @Test
    void deveBuscarPedidosPorStatusComSucesso() {
        CustomerOrder order = mock(CustomerOrder.class);
        List<CustomerOrder> orders = Collections.singletonList(order);
        when(gateway.getCustomerOrderByStatusList(anyList(), anyBoolean())).thenReturn(orders);
        List<CustomerOrder> result = useCase.getByStatusList(Collections.singletonList(CustomerOrderStatus.CHECKOUT.getDescription()), true);
        assertFalse(result.isEmpty());
        verify(gateway).getCustomerOrderByStatusList(anyList(), eq(true));
    }

    @Test
    void deveLancarExcecaoQuandoNaoExistemPedidosComStatus() {
        when(gateway.getCustomerOrderByStatusList(anyList(), anyBoolean())).thenReturn(Collections.emptyList());
        assertThrows(CustomerOrderException.class, () -> useCase.getByStatusList(Collections.singletonList("INEXISTENTE"), true));
    }
}

