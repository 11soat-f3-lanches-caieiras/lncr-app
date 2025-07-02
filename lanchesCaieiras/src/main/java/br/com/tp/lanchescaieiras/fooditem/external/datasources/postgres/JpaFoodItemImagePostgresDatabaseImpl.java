package br.com.tp.lanchescaieiras.fooditem.external.datasources.postgres;

import br.com.tp.lanchescaieiras.commons.dtos.FoodItemImageDTO;
import br.com.tp.lanchescaieiras.fooditem.external.storage.FoodItemImageStorageImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JpaFoodItemImagePostgresDatabaseImpl {

    public final JpaFoodItemImagePostgresRepository jpaFoodItemImagePostgresRepository;
    public final FoodItemImageStorageImpl foodItemImageStorage;
    public final JpaFoodItemPostgresMapper foodItemMapper;

    public JpaFoodItemImagePostgresDatabaseImpl(JpaFoodItemImagePostgresRepository jpaFoodItemImagePostgresRepository,
                                                FoodItemImageStorageImpl foodItemImageStorage,
                                                JpaFoodItemPostgresMapper foodItemMapper) {
        this.jpaFoodItemImagePostgresRepository = jpaFoodItemImagePostgresRepository;
        this.foodItemImageStorage = foodItemImageStorage;
        this.foodItemMapper = foodItemMapper;
    }

    public void saveAll(List<FoodItemImageDTO> foodItemImageDTOList) {
        List<JpaFoodItemImagePostgresEntity> jpaFoodItemImageEntityList = foodItemMapper.toJpaFoodItemImageEntityList(foodItemImageDTOList);
        this.jpaFoodItemImagePostgresRepository.saveAll(jpaFoodItemImageEntityList);
    }

    public List<FoodItemImageDTO> findAllByFoodItemId(Integer id) {
        return this.jpaFoodItemImagePostgresRepository.findAllByFoodItemId(id)
                .stream()
                .map(foodItemMapper::toFoodItemImageDTO)
                .toList();
    }

    public void deleteByFoodItemId(List<FoodItemImageDTO> foodItemImageDTOList) {
        this.jpaFoodItemImagePostgresRepository.deleteAll(this.foodItemMapper.toJpaFoodItemImageEntityList(foodItemImageDTOList));
    }

    public void save(FoodItemImageDTO foodItemImageDTO) {
        this.jpaFoodItemImagePostgresRepository.save(this.foodItemMapper.toJpaFoodItemImageEntity(foodItemImageDTO));
    }

    public FoodItemImageDTO findById(Integer foodItemImageId) {
        return this.foodItemMapper.toFoodItemImageDTO(
                this.jpaFoodItemImagePostgresRepository.findById(foodItemImageId)
                        .orElse(null));
    }
}
