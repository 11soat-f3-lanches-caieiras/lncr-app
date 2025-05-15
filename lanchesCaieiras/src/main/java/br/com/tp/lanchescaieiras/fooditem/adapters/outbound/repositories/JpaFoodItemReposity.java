package br.com.tp.lanchescaieiras.fooditem.adapters.outbound.repositories;

import br.com.tp.lanchescaieiras.customer.adapters.outbound.entities.JpaCustomerEntity;
import br.com.tp.lanchescaieiras.fooditem.adapters.outbound.entities.JpaFoodItemEntity;
import br.com.tp.lanchescaieiras.fooditem.domain.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaFoodItemReposity extends JpaRepository<JpaFoodItemEntity,Integer> {

    JpaFoodItemEntity save(JpaFoodItemEntity jpaFoodItemEntity);

    }
