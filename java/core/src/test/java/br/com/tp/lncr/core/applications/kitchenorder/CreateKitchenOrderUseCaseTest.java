package br.com.tp.lncr.core.applications.kitchenorder;

import br.com.tp.lncr.core.commons.dtos.kitchenorder.KitchenOrderDTO;
import br.com.tp.lncr.core.commons.exceptions.KitchenOrderException;
import br.com.tp.lncr.core.commons.interfaces.kitchenorder.KitchenOrderGateway;
import br.com.tp.lncr.core.domain.kitchenorder.KitchenOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreateKitchenOrderUseCaseTest {
    private KitchenOrderGateway kitchenOrderGateway;
    private CreateKitchenOrderUseCase useCase;

    @BeforeEach
    void setUp() {
        kitchenOrderGateway = mock(KitchenOrderGateway.class);
        useCase = new CreateKitchenOrderUseCase(kitchenOrderGateway);
    }

    @Test
    void deveCriarNovoPreparoComSucesso() {
        KitchenOrderDTO dto = new KitchenOrderDTO();
        dto.setCustomerOrderId(1);
        when(kitchenOrderGateway.getKitchenOrderByCustomerOrderId(1)).thenReturn(null);
        KitchenOrder order = mock(KitchenOrder.class);
        when(kitchenOrderGateway.saveKitchenOrder(any())).thenReturn(order);
        when(order.getId()).thenReturn(10);

        KitchenOrder result = useCase.execute(dto);
        assertNotNull(result);
        verify(kitchenOrderGateway).sendNotification(eq("KITCHEN_ORDER_RECEIVED"), eq(10), contains("Novo preparo"));
    }

    @Test
    void deveLancarExcecaoSePreparoJaExiste() {
        KitchenOrderDTO dto = new KitchenOrderDTO();
        dto.setCustomerOrderId(2);
        when(kitchenOrderGateway.getKitchenOrderByCustomerOrderId(2)).thenReturn(new KitchenOrder());
        KitchenOrderException ex = assertThrows(KitchenOrderException.class, () -> useCase.execute(dto));
        assertEquals(409, ex.getCode());
    }
}

