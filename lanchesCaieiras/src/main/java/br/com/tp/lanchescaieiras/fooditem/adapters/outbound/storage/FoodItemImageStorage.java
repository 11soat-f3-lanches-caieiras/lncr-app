package br.com.tp.lanchescaieiras.fooditem.adapters.outbound.storage;

import br.com.tp.lanchescaieiras.fooditem.adapters.outbound.entities.JpaFoodItemImageEntity;
import br.com.tp.lanchescaieiras.fooditem.infraestructure.exceptions.FoodItemException;

public interface FoodItemImageStorage {
    void saveImageFile(JpaFoodItemImageEntity jpaFoodItemImageEntity) throws FoodItemException;

    void deleteImageFile(String fileName) throws FoodItemException;

    String getImgaeData(String fileName) throws FoodItemException;

}
