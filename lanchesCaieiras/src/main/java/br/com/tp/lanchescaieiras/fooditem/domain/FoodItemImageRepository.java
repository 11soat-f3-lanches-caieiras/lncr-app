package br.com.tp.lanchescaieiras.fooditem.domain;

import br.com.tp.lanchescaieiras.commons.domain.Image;

public interface FoodItemImageRepository {

    Image save(Image image);
    FoodItem saveImages(FoodItem foodItem);

}
