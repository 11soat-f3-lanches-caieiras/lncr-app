package br.com.tp.lanchescaieiras.fooditem.adapters.outbound.repositories;

import br.com.tp.lanchescaieiras.customer.adapters.outbound.entities.JpaCustomerEntity;
import br.com.tp.lanchescaieiras.fooditem.adapters.outbound.entities.JpaFoodItemEntity;
import br.com.tp.lanchescaieiras.fooditem.adapters.outbound.entities.JpaFoodItemImageEntity;
import br.com.tp.lanchescaieiras.fooditem.domain.FoodItem;
import br.com.tp.lanchescaieiras.fooditem.domain.FoodItemRepository;
import br.com.tp.lanchescaieiras.fooditem.infraestructure.exceptions.FoodItemException;
import br.com.tp.lanchescaieiras.fooditem.mappers.FoodItemMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JpaFoodItemRepositoryImpl implements FoodItemRepository {

    public final JpaFoodItemReposity jpaFoodItemReposity;
    public final FoodItemMapper foodItemMapper;
    private final JpaFoodItemImageEntity jpaFoodItemImageEntity;

    public JpaFoodItemRepositoryImpl(JpaFoodItemReposity jpaFoodItemReposity, FoodItemMapper foodItemMapper, JpaFoodItemImageEntity jpaFoodItemImageEntity) {
        this.jpaFoodItemReposity = jpaFoodItemReposity;
        this.foodItemMapper = foodItemMapper;
        this.jpaFoodItemImageEntity = jpaFoodItemImageEntity;
    }

    @Override
    public FoodItem save(FoodItem foodItem) {
        JpaFoodItemEntity jpaFoodItemEntity = foodItemMapper.domainToJpa(foodItem);
        JpaFoodItemEntity savedEntity = jpaFoodItemReposity.save(jpaFoodItemEntity);
        FoodItem createdFoodItem = foodItemMapper.jpaToDomain(savedEntity);
        createdFoodItem.setImages(foodItem.getImages());
        return createdFoodItem;
    }

    @Override
    public List<FoodItem> findAllByCategory(Integer _limit, String category) {
        if (category != null && !category.isEmpty()) {
            return jpaFoodItemReposity.findAllByCategory(_limit, category).stream()
                    .map(foodItemMapper::jpaToDomain)
                    .toList();
        }
        return findAll(_limit);
    }

    @Override
    public List<FoodItem> findAll(Integer _limit) {
        return jpaFoodItemReposity.findAll(Pageable.ofSize(_limit)).stream()
                .map(foodItemMapper::jpaToDomain)
                .toList();
    }

    @Override
    public Optional<FoodItem> findById(Integer id) {
        Optional<FoodItem> foodItem = jpaFoodItemReposity.findById(id)
                .map(foodItemMapper::jpaToDomain);
        return foodItem;
    }

    @Override
    public Optional<FoodItem> partialUpdateFoodItemById(Integer id, FoodItem foodItem) {
        return this.jpaFoodItemReposity.findById(id).map(existingEntity -> {
            if (foodItem.getDescription() != null) {
                existingEntity.setDescription(foodItem.getDescription());
            }
            if (foodItem.getPrice() != null) {
                existingEntity.setPrice(foodItem.getPrice());
            }
            JpaFoodItemEntity updatedEntity = this.jpaFoodItemReposity.save(existingEntity);
            return foodItemMapper.jpaToDomain(updatedEntity);
        });

    }

    @Override
    public void deleteFoodItemById(Integer id) {
            try {
                jpaFoodItemReposity.delete(this.jpaFoodItemReposity.findById(id).get());
            } catch (Exception e) {
                throw new FoodItemException("Erro ao deletar o item de alimentação com ID: " + id, 500);
            }
        }


}
