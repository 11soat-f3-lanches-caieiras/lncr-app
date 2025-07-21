package br.com.tp.lncr.core.adapters.kitchenorder;

import br.com.tp.lncr.core.commons.dtos.kitchenorder.KitchenOrderDTO;
import br.com.tp.lncr.core.commons.dtos.kitchenorder.KitchenOrderFoodItemDTO;
import br.com.tp.lncr.core.commons.interfaces.kitchenorder.KitchenOrderDatabase;
import br.com.tp.lncr.core.domain.kitchenorder.KitchenOrder;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class KitchenOrderGatewayImplTest {
    private final KitchenOrderFoodItemDTO KITCHEN_FOOD_ITEM_DTO = new KitchenOrderFoodItemDTO(1, 1, "Coca", "Lata 350 ml", "Copo com gelo e limão");
    private final KitchenOrderDTO KITCHEN_ORDER_DTO = new KitchenOrderDTO(1, 1, "PREPARING", List.of(KITCHEN_FOOD_ITEM_DTO), null, null);

    @Test
    void testSaveKitchenOrder() {
        KitchenOrderDatabase db = mock(KitchenOrderDatabase.class);
        KitchenOrderMapper mapper = new KitchenOrderMapper();
        KitchenOrderGatewayImpl gateway = new KitchenOrderGatewayImpl(db, mapper);

        KitchenOrderDTO dto = KITCHEN_ORDER_DTO;
        KitchenOrder order = new KitchenOrder(dto);
        when(db.save(any())).thenReturn(dto);
        assertNotNull(gateway.saveKitchenOrder(order));
    }

    @Test
    void testGetKitchenOrderByCustomerOrderId() {
        KitchenOrderDatabase db = mock(KitchenOrderDatabase.class);
        KitchenOrderMapper mapper = new KitchenOrderMapper();
        KitchenOrderGatewayImpl gateway = new KitchenOrderGatewayImpl(db, mapper);
        when(db.findByCustomerOrderId(1, false)).thenReturn(KITCHEN_ORDER_DTO);
        assertNotNull(gateway.getKitchenOrderByCustomerOrderId(1));
    }

    @Test
    void testGetKitchenOrderById() {
        KitchenOrderDatabase db = mock(KitchenOrderDatabase.class);
        KitchenOrderMapper mapper = new KitchenOrderMapper();
        KitchenOrderGatewayImpl gateway = new KitchenOrderGatewayImpl(db, mapper);
        when(db.findById(anyInt(), anyBoolean())).thenReturn(KITCHEN_ORDER_DTO);
        assertNotNull(gateway.getKitchenOrderById(1));
    }

    @Test
    void testUpdateCustomerOrderStatus() {
        KitchenOrderDatabase db = mock(KitchenOrderDatabase.class);
        KitchenOrderMapper mapper = new KitchenOrderMapper();
        KitchenOrderGatewayImpl gateway = new KitchenOrderGatewayImpl(db, mapper);
        doNothing().when(db).updateCustomerOrderStatus(anyInt(), anyString());
        gateway.updateCustomerOrderStatus(1, "READY");
        verify(db).updateCustomerOrderStatus(1, "READY");
    }

    @Test
    void testSendNotification() {
        KitchenOrderDatabase db = mock(KitchenOrderDatabase.class);
        KitchenOrderMapper mapper = new KitchenOrderMapper();
        KitchenOrderGatewayImpl gateway = new KitchenOrderGatewayImpl(db, mapper);
        doNothing().when(db).sendNotification(anyString(), anyInt(), anyString());
        gateway.sendNotification("type", 1, "msg");
        verify(db).sendNotification("type", 1, "msg");
    }
}

