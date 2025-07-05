package br.com.tp.lanchescaieiras._core.applications.customerorder.mappers;

import br.com.tp.lanchescaieiras._core.domain.customerorder.CustomerOrderFoodItem;
import br.com.tp.lanchescaieiras._core.domain.kitchenorder.KitchenOrderFoodItem;
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
