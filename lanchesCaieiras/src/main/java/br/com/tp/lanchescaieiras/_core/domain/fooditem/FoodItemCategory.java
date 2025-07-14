package br.com.tp.lanchescaieiras._core.domain.fooditem;

import br.com.tp.lanchescaieiras._core.commons.interfaces.EnumWithIdDescription;
import br.com.tp.lanchescaieiras._core.commons.utils.EnumUtils;
import br.com.tp.lanchescaieiras._core.domain.exceptions.FoodItemException;

public enum FoodItemCategory implements EnumWithIdDescription {
    SANDWICH(1, "Sandwich"),
    DRINK(2, "Drink"),
    DESSERT(3, "Dessert"),
    SNACK(4, "Snack");

    private final Integer id;
    private final String description;

    FoodItemCategory(int id, String description) {
        this.id = id;
        this.description = description;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public static FoodItemCategory fromId(Integer id) {
        return EnumUtils.fromId(FoodItemCategory.class, id,
                new FoodItemException("Id da categoria inválido: " + id + ". Os ids válidos são: " + listOfAllowIds(), 400));
    }

    public static FoodItemCategory fromDescription(String description) {
        return EnumUtils.fromDescription(FoodItemCategory.class, description,
                new FoodItemException("Categoria inválida: " + description + ". As categorias válidas são: " + listOfAllowDescriptions(), 400));
    }

    public static String listOfAllowDescriptions() {
        return EnumUtils.listOfAllowDescriptions(FoodItemCategory.class);
    }

    public static String listOfAllowIds() {
        return EnumUtils.listOfAllowIds(FoodItemCategory.class);
    }
}
