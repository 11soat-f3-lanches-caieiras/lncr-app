package br.com.tp.lanchescaieiras.fooditem.external.datasources.postgres;

import br.com.tp.lanchescaieiras.commons.dtos.FoodItemDTO;
import br.com.tp.lanchescaieiras.fooditem.external.config.FoodItemConfig;
import br.com.tp.lanchescaieiras.fooditem.external.storage.FoodItemImageStorageImpl;
import br.com.tp.lanchescaieiras.commons.interfaces.FoodItemDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static java.util.Arrays.stream;

@Repository
public class JpaFoodItemPostgresDatabaseImpl {

    public final JpaFoodItemPostgresReposity jpaFoodItemPostgresReposity;
    public final FoodItemImageStorageImpl foodItemImageStorage;
    public final JpaFoodItemPostgresMapper jpaFoodItemPostgresMapper;
    public final FoodItemConfig foodItemConfig;

    public JpaFoodItemPostgresDatabaseImpl(JpaFoodItemPostgresReposity jpaFoodItemPostgresReposity, FoodItemImageStorageImpl foodItemImageStorage, JpaFoodItemPostgresMapper jpaFoodItemPostgresMapper, FoodItemConfig foodItemConfig) {
        this.jpaFoodItemPostgresReposity = jpaFoodItemPostgresReposity;
        this.foodItemImageStorage = foodItemImageStorage;
        this.jpaFoodItemPostgresMapper = jpaFoodItemPostgresMapper;
        this.foodItemConfig = foodItemConfig;
    }

    public FoodItemDTO save(FoodItemDTO foodItemDTO) {
        JpaFoodItemPostgresEntity jpaFoodItemPostgresEntity = jpaFoodItemPostgresMapper.toJpaFoodItemPostgresEntity(foodItemDTO);
        jpaFoodItemPostgresEntity = jpaFoodItemPostgresReposity.save(jpaFoodItemPostgresEntity);
        return jpaFoodItemPostgresMapper.toFoodItemDTO(jpaFoodItemPostgresEntity);
    }

    public boolean existsByName(String foodItemName) {
        return jpaFoodItemPostgresReposity.existsByName(foodItemName);
    }

    public List<FoodItemDTO> getAllFoodItems(Integer _limit) {
        return jpaFoodItemPostgresReposity.findAll(Pageable.ofSize(_limit))
                .stream()
                .map(jpaFoodItemPostgresMapper::toFoodItemDTO)
                .toList();
    }

    public List<FoodItemDTO> getAllFoodItemsByCategory(Integer _limit, Integer categoryId) {
        return this.jpaFoodItemPostgresReposity.findAllByCategory(_limit,categoryId)
                .stream()
                .map(jpaFoodItemPostgresMapper::toFoodItemDTO)
                .toList();
    }

   /* @Override
    public List<FoodItemDTO> findAll(Integer _limit) {
        *//*return jpaFoodItemPostgresReposity.findAll(Pageable.ofSize(_limit)).stream()
                .map(foodItemMapper::jpaToDomain)
                .toList();*//*
        return null;
    }

    @Override
    public List<FoodItemDTO> findAllByCategory(Integer _limit, String category) {
       *//* if (category != null && !category.isEmpty()) {
            return jpaFoodItemPostgresReposity.findAllByCategory(_limit, category).stream()
                    .map(foodItemMapper::jpaToDomain)
                    .toList();
        }
        return findAll(_limit);*//*
        return null;
    }



    @Override
    public Optional<FoodItemDTO> findById(Integer id) {
        *//*Optional<br.com.tp.lanchescaieiras.fooditem.domain.FoodItem> foodItem = jpaFoodItemPostgresReposity.findById(id)
                .map(foodItemMapper::jpaToDomain);*//*
        return null;
    }

    @Override
    public Optional<FoodItemDTO> partialUpdateFoodItemById(Integer id, FoodItemDTO foodItemDTO) {
        *//*return this.jpaFoodItemPostgresReposity.findById(id).map(existingEntity -> {
            if (foodItemDTO.getDescription() != null) {
                existingEntity.setDescription(foodItemDTO.getDescription());
            }
            if (foodItemDTO.getPrice() != null) {
                existingEntity.setPrice(foodItemDTO.getPrice());
            }
            JpaFoodItemPostgresEntity updatedEntity = this.jpaFoodItemPostgresReposity.save(existingEntity);
            return foodItemMapper.jpaToDomain(updatedEntity);
        });*//*
        return null;

    }

    @Override
    public void deleteFoodItemById(Integer id) {
        *//*try {
            jpaFoodItemPostgresReposity.delete(this.jpaFoodItemPostgresReposity.findById(id).get());

        } catch (Exception e) {
            throw new FoodItemException("Erro ao deletar o item de alimentação com ID: " + id, 500);
        }*//*
    }*/


}
