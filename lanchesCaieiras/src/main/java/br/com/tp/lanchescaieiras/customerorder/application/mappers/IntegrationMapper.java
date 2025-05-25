package br.com.tp.lanchescaieiras.customerorder.application.mappers;

import br.com.tp.lanchescaieiras.customerorder.domain.CustomerOrderFoodItem;
import br.com.tp.lanchescaieiras.kitchenorder.domain.KitchenOrderFoodItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;


@Mapper(componentModel = "spring")
public interface IntegrationMapper {

    @Mappings({
            @Mapping(target = "id", source = "customerOrderFoodItem.id"),
            @Mapping(target = "name", source = "customerOrderFoodItem.name"),
            @Mapping(target = "description", source = "customerOrderFoodItem.description"),
            @Mapping(target = "notes", source = "customerOrderFoodItem.notes"),
    })
    KitchenOrderFoodItem orderToKichen(CustomerOrderFoodItem customerOrderFoodItem);
}
