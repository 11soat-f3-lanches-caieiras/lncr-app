package br.com.tp.lncr.core.adapters.fooditem;

import br.com.tp.lncr.core.commons.dtos.fooditem.FoodItemImageDTO;
import br.com.tp.lncr.core.domain.fooditem.FoodItemImage;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class FoodItemImagePresenterTest {
    @Test
    void testCreated() {
        FoodItemImagePresenter presenter = new FoodItemImagePresenter(new FoodItemMapper());
        FoodItemImage image = new FoodItemImage();
        FoodItemImageDTO dto = presenter.created(image, "/img");
        assertNotNull(dto);
    }

    @Test
    void testGetById() {
        FoodItemImagePresenter presenter = new FoodItemImagePresenter(new FoodItemMapper());
        assertNotNull(presenter.getById(new FoodItemImage(), "/img"));
    }

    @Test
    void testUpdateById() {
        FoodItemImagePresenter presenter = new FoodItemImagePresenter(new FoodItemMapper());
        assertNotNull(presenter.updateById(new FoodItemImage(), "/img"));
    }

    @Test
    void testGetAllImagesByFoodItemId() {
        FoodItemImagePresenter presenter = new FoodItemImagePresenter(new FoodItemMapper());
        assertNotNull(presenter.getAllImagesByFoodItemId(Collections.emptyList(), "/img", false));
    }
}

