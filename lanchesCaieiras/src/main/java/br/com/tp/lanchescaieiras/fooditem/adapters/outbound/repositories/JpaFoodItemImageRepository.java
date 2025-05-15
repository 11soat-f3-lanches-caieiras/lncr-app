package br.com.tp.lanchescaieiras.fooditem.adapters.outbound.repositories;

import br.com.tp.lanchescaieiras.fooditem.adapters.outbound.entities.JpaFoodItemImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaFoodItemImageRepository extends JpaRepository<JpaFoodItemImageEntity,Integer> {

    JpaFoodItemImageEntity save(JpaFoodItemImageEntity jpaFoodItemImageEntity);

    Optional<JpaFoodItemImageEntity> findById(Integer id);

}
