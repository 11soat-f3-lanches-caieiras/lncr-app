package br.com.tp.lncr.core.applications.kitchenorder;

import br.com.tp.lncr.core.commons.enums.KitchenOrderStatus;
import br.com.tp.lncr.core.commons.exceptions.KitchenOrderException;
import br.com.tp.lncr.core.commons.interfaces.kitchenorder.KitchenOrderGateway;
import br.com.tp.lncr.core.domain.kitchenorder.KitchenOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class GetKitchenOrderUseCaseTest {
    private KitchenOrderGateway kitchenOrderGateway;
    private GetKitchenOrderUseCase useCase;

    @BeforeEach
    void setUp() {
        kitchenOrderGateway = mock(KitchenOrderGateway.class);
        useCase = new GetKitchenOrderUseCase(kitchenOrderGateway);
    }

    @Test
    void deveBuscarPorIdComSucesso() {
        KitchenOrder order = new KitchenOrder();
        when(kitchenOrderGateway.getKitchenOrderById(1, true)).thenReturn(order);
        KitchenOrder result = useCase.getById(1, true);
        assertEquals(order, result);
    }

    @Test
    void deveLancarExcecaoSeNaoEncontrarPorId() {
        when(kitchenOrderGateway.getKitchenOrderById(2, false)).thenReturn(null);
        assertThrows(KitchenOrderException.class, () -> useCase.getById(2, false));
    }

    @Test
    void deveBuscarPorCustomerOrderIdComSucesso() {
        KitchenOrder order = new KitchenOrder();
        when(kitchenOrderGateway.getKitchenOrderByCustomerOrderId(3, false)).thenReturn(order);
        KitchenOrder result = useCase.getByCustomerOrderId(3, false);
        assertEquals(order, result);
    }

    @Test
    void deveLancarExcecaoSeNaoEncontrarPorCustomerOrderId() {
        when(kitchenOrderGateway.getKitchenOrderByCustomerOrderId(4, true)).thenReturn(null);
        assertThrows(KitchenOrderException.class, () -> useCase.getByCustomerOrderId(4, true));
    }

    @Test
    void deveBuscarPorStatusListComSucesso() {
        List<KitchenOrder> orders = Arrays.asList(new KitchenOrder(), new KitchenOrder());
        when(kitchenOrderGateway.getKitchenOrderByStatusList(anyList(), eq(false))).thenReturn(orders);
        List<KitchenOrder> result = useCase.getByStatusList(Collections.singletonList(KitchenOrderStatus.RECEIVED.getDescription()), false);
        assertEquals(2, result.size());
    }

    @Test
    void deveLancarExcecaoSeNaoEncontrarPorStatusList() {
        when(kitchenOrderGateway.getKitchenOrderByStatusList(anyList(), eq(true))).thenReturn(Collections.emptyList());
        assertThrows(KitchenOrderException.class, () -> useCase.getByStatusList(Collections.singletonList("RECEIVED"), true));
    }
}

