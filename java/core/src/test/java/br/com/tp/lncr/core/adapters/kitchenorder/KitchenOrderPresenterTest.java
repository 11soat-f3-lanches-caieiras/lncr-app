package br.com.tp.lncr.core.adapters.kitchenorder;

import br.com.tp.lncr.core.commons.dtos.kitchenorder.KitchenOrderDTO;
import br.com.tp.lncr.core.domain.kitchenorder.KitchenOrder;
import br.com.tp.lncr.core.domain.kitchenorder.KitchenOrderFoodItem;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class KitchenOrderPresenterTest {
    @Test
    void testCreatedClearsKitchenOrderIdInFoodItem() {
        KitchenOrderMapper mapper = new KitchenOrderMapper();
        KitchenOrderPresenter presenter = new KitchenOrderPresenter(mapper);
        KitchenOrder order = new KitchenOrder();
        KitchenOrderFoodItem item = new KitchenOrderFoodItem();
        item.setKitchenOrderId(123);
        order.setFoodItems(Collections.singletonList(item));
        KitchenOrderDTO dto = presenter.created(order);
        assertNull(order.getFoodItems().get(0).getKitchenOrderId());
        assertNotNull(dto);
    }

    @Test
    void testGetByIdAndGetByCustomerOrderId() {
        KitchenOrderMapper mapper = new KitchenOrderMapper();
        KitchenOrderPresenter presenter = new KitchenOrderPresenter(mapper);
        KitchenOrder order = new KitchenOrder();
        KitchenOrderFoodItem item = new KitchenOrderFoodItem();
        item.setKitchenOrderId(123);
        order.setFoodItems(Collections.singletonList(item));
        assertNull(presenter.getById(order).getFoodItems().get(0).getKitchenOrderId());
        assertNull(presenter.getByCustomerOrderId(order).getFoodItems().get(0).getKitchenOrderId());
    }
}
