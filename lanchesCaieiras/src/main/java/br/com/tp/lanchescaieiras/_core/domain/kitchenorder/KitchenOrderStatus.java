package br.com.tp.lanchescaieiras._core.domain.kitchenorder;

import br.com.tp.lanchescaieiras._core.commons.interfaces.EnumWithIdDescription;
import br.com.tp.lanchescaieiras._core.commons.utils.EnumUtils;
import br.com.tp.lanchescaieiras._core.domain.exceptions.CustomerOrderException;

public enum KitchenOrderStatus implements EnumWithIdDescription {
    CANCELLED(0,"Cancelled"),
    RECEIVED(1, "Received"),
    PREPARING(2, "Preparing"),
    READY(3, "Ready"),
    FINISHED(4, "Finished");

    private final Integer id;
    private final String description;

    KitchenOrderStatus(int id, String description) {
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

    public static KitchenOrderStatus fromId(Integer id) {
        return EnumUtils.fromId(KitchenOrderStatus.class, id,
                new CustomerOrderException("Id do status inválido: " + id + ". Os ids de status válidos são: " + listOfAllowIds(), 400));
    }

    public static KitchenOrderStatus fromDescription(String description) {
        return EnumUtils.fromDescription(KitchenOrderStatus.class, description,
                new CustomerOrderException("Status inválidos: " + description + ". Os status válidos são: " + listOfAllowDescriptions(), 400));
    }

    public static String listOfAllowDescriptions() {
        return EnumUtils.listOfAllowDescriptions(KitchenOrderStatus.class);
    }

    public static String listOfAllowIds() {
        return EnumUtils.listOfAllowIds(KitchenOrderStatus.class);
    }
}
