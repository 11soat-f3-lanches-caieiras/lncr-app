package br.com.tp.lanchescaieiras.fooditem.mappers;

import br.com.tp.lanchescaieiras.fooditem.adapters.outbound.entities.JpaFoodItemEntity;
import br.com.tp.lanchescaieiras.fooditem.domain.FoodItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface FoodItemMapper {

    @Mappings({
            @Mapping(target = "id", source = "foodItem.id"),
            @Mapping(target = "name", source = "foodItem.name"),
            @Mapping(target = "description", source = "foodItem.description"),
            @Mapping(target = "price", source = "foodItem.price"),
            @Mapping(target = "category", source = "category")
    })
    JpaFoodItemEntity domainToJpa(FoodItem foodItem);

    @Mappings({
            @Mapping(target = "id", source = "jpaFoodItemEntity.id"),
            @Mapping(target = "name", source = "jpaFoodItemEntity.name"),
            @Mapping(target = "description", source = "jpaFoodItemEntity.description"),
            @Mapping(target = "price", source = "jpaFoodItemEntity.price"),
            @Mapping(target = "category", source = "category")
    })
    FoodItem jpaToDomain(JpaFoodItemEntity jpaFoodItemEntity);





}
