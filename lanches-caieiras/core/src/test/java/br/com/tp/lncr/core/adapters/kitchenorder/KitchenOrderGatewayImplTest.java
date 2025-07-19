package br.com.tp.lncr.core.adapters.kitchenorder;

import br.com.tp.lncr.core.commons.dtos.kitchenorder.KitchenOrderDTO;
import br.com.tp.lncr.core.commons.interfaces.kitchenorder.KitchenOrderDatabase;
import br.com.tp.lncr.core.domain.kitchenorder.KitchenOrder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class KitchenOrderGatewayImplTest {
    @Test
    void testSaveKitchenOrder() {
        KitchenOrderDatabase db = mock(KitchenOrderDatabase.class);
        KitchenOrderMapper mapper = new KitchenOrderMapper();
        KitchenOrderGatewayImpl gateway = new KitchenOrderGatewayImpl(db, mapper);
        KitchenOrder order = new KitchenOrder();
        KitchenOrderDTO dto = new KitchenOrderDTO();
        when(db.save(any())).thenReturn(dto);
        assertNotNull(gateway.saveKitchenOrder(order));
    }

    @Test
    void testGetKitchenOrderByCustomerOrderId() {
        KitchenOrderDatabase db = mock(KitchenOrderDatabase.class);
        KitchenOrderMapper mapper = new KitchenOrderMapper();
        KitchenOrderGatewayImpl gateway = new KitchenOrderGatewayImpl(db, mapper);
        when(db.findByCustomerOrderId(anyInt(), anyBoolean())).thenReturn(new KitchenOrderDTO());
        assertNotNull(gateway.getKitchenOrderByCustomerOrderId(1));
    }

    @Test
    void testGetKitchenOrderById() {
        KitchenOrderDatabase db = mock(KitchenOrderDatabase.class);
        KitchenOrderMapper mapper = new KitchenOrderMapper();
        KitchenOrderGatewayImpl gateway = new KitchenOrderGatewayImpl(db, mapper);
        when(db.findById(anyInt(), anyBoolean())).thenReturn(new KitchenOrderDTO());
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

