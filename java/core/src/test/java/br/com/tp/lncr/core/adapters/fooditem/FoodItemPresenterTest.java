package br.com.tp.lncr.core.adapters.fooditem;

import br.com.tp.lncr.core.commons.dtos.fooditem.FoodItemDTO;
import br.com.tp.lncr.core.domain.fooditem.FoodItem;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class FoodItemPresenterTest {
    @Test
    void testCreated() {
        FoodItemPresenter presenter = new FoodItemPresenter(new FoodItemMapper());
        FoodItem item = new FoodItem();
        FoodItemDTO dto = presenter.created(item, "/img");
        assertNotNull(dto);
    }

    @Test
    void testGetAll() {
        FoodItemPresenter presenter = new FoodItemPresenter(new FoodItemMapper());
        assertNotNull(presenter.getAll(Collections.emptyList(), "/img"));
    }

    @Test
    void testGetById() {
        FoodItemPresenter presenter = new FoodItemPresenter(new FoodItemMapper());
        assertNotNull(presenter.getById(new FoodItem(), "/img"));
    }

    @Test
    void testGetByIdList() {
        FoodItemPresenter presenter = new FoodItemPresenter(new FoodItemMapper());
        assertNotNull(presenter.getByIdList(Collections.emptyList()));
    }

    @Test
    void testPartialUpdateById() {
        FoodItemPresenter presenter = new FoodItemPresenter(new FoodItemMapper());
        assertNotNull(presenter.patialUpdateById(new FoodItem(), "/img"));
    }
}

