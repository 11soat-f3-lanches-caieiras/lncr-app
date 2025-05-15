package br.com.tp.lanchescaieiras.fooditem.application.services;

import br.com.tp.lanchescaieiras.fooditem.adapters.outbound.entities.JpaFoodItemEntity;
import br.com.tp.lanchescaieiras.fooditem.adapters.outbound.repositories.JpaFoodItemRepositoryImpl;
import br.com.tp.lanchescaieiras.fooditem.application.usecases.FoodItemUseCases;
import br.com.tp.lanchescaieiras.fooditem.domain.FoodItem;
import org.springframework.stereotype.Service;

@Service
public class FoodItemServicesImpl implements FoodItemUseCases {

    public final JpaFoodItemRepositoryImpl jpaFoodItemRepository;

    public FoodItemServicesImpl(JpaFoodItemRepositoryImpl jpaFoodItemRepository) {
        this.jpaFoodItemRepository = jpaFoodItemRepository;
    }

    @Override
    public FoodItem createFoodItem(FoodItem foodItem) {
        return this.jpaFoodItemRepository.save(foodItem);
    }
}
