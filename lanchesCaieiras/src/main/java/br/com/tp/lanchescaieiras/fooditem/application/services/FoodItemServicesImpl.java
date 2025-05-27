package br.com.tp.lanchescaieiras.fooditem.application.services;

import br.com.tp.lanchescaieiras.customer.infraestructure.exceptions.CustomerException;
import br.com.tp.lanchescaieiras.fooditem.adapters.outbound.repositories.JpaFoodItemImageRepositoryImpl;
import br.com.tp.lanchescaieiras.fooditem.adapters.outbound.repositories.JpaFoodItemRepositoryImpl;
import br.com.tp.lanchescaieiras.fooditem.application.usecases.FoodItemUseCases;
import br.com.tp.lanchescaieiras.fooditem.domain.FoodItem;
import br.com.tp.lanchescaieiras.fooditem.domain.FoodItemImage;
import br.com.tp.lanchescaieiras.fooditem.infraestructure.exceptions.FoodItemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodItemServicesImpl implements FoodItemUseCases {

    private static final Logger log = LoggerFactory.getLogger(FoodItemServicesImpl.class);
    public final JpaFoodItemRepositoryImpl jpaFoodItemRepository;
    public final JpaFoodItemImageRepositoryImpl jpaFoodItemImageRepository;

    public FoodItemServicesImpl(JpaFoodItemRepositoryImpl jpaFoodItemRepository, JpaFoodItemImageRepositoryImpl jpaFoodItemImageRepository) {
        this.jpaFoodItemRepository = jpaFoodItemRepository;
        this.jpaFoodItemImageRepository = jpaFoodItemImageRepository;
    }

    @Override
    public FoodItem createFoodItem(FoodItem foodItem) {
        log.info("Criando novo item de alimentação");
        foodItem = this.jpaFoodItemRepository.save(foodItem);
        return jpaFoodItemImageRepository.saveImages(foodItem);
    }

    @Override
    public List<FoodItem> getAllFoodItems(Integer _limit, String category) {
        log.info("Buscando lista de items de alimentação por categoria");
        List<FoodItem> foodItemList = jpaFoodItemRepository.findAllByCategory(_limit, category);
        log.info("Buscando localização das imagens");
        for (FoodItem foodItem : foodItemList) {

            foodItem = jpaFoodItemImageRepository.findAllImagesByFoodItemId(foodItem);
        }
        return foodItemList;
    }

    @Override
    public Optional<FoodItem> getFoodItem(Integer id) {
        Optional<FoodItem> foodItem = jpaFoodItemRepository.findById(id);
        if (foodItem.isPresent()) {
            foodItem = Optional.of(jpaFoodItemImageRepository.findAllImagesByFoodItemId(foodItem.get()));
        } else {
            throw new FoodItemException("Item de alimentação não encontrado com o ID: " + id, 404);
        }
        return foodItem;
    }

    @Override
    public FoodItem partialUpdateFoodItemById(Integer id, FoodItem foodItem) {
        return this.jpaFoodItemRepository.partialUpdateFoodItemById(id, foodItem)
                .orElseThrow(() -> new CustomerException("Item de Alimentação não encontrado com o ID: " + id, 404));
    }

    @Override
    public void deleteFoodItemById(Integer id) {
        try {
            this.jpaFoodItemRepository.deleteFoodItemById(id);
        } catch (Exception e) {
            throw new FoodItemException("Item de alimentação não encontrado com o ID: " + id, 404);
        }
    }

    @Override
    public FoodItemImage getImageData(Integer id) {
        return jpaFoodItemImageRepository.findImageById(id);
    }

    @Override
    public FoodItemImage updateImageById(Integer id, FoodItemImage foodItemImage) {
        return this.jpaFoodItemImageRepository.updateImageById(id, foodItemImage);
    }

}

