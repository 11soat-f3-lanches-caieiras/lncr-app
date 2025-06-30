package br.com.tp.lanchescaieiras.customerorder.domain;

import br.com.tp.lanchescaieiras.commons.interfaces.EnumWithIdDescription;
import br.com.tp.lanchescaieiras.commons.utils.EnumUtils;
import br.com.tp.lanchescaieiras.customerorder.infraestructure.exceptions.CustomerOrderException;
import jakarta.persistence.Id;

public enum CustomerOrderStatus implements EnumWithIdDescription {
    CHECKOUT(1, "Checkout"),
    RECEIVED(2, "Received"),
    PREPARING(3, "Preparing"),
    READY(4, "Ready"),
    FINISHED(5, "Finished"),
    CANCELED(6, "Canceled");

    @Id
    private final int id;
    private final String description;

    CustomerOrderStatus(Integer id, String description) {
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

    public static CustomerOrderStatus fromId(Integer id) {
        return EnumUtils.fromId(CustomerOrderStatus.class, id,
            new CustomerOrderException("Id do status inválido: " + id + ". Os ids de status válidos são: " + listOfAllowIds(), 400));
    }

    public static CustomerOrderStatus fromDescription(String description) {
        return EnumUtils.fromDescription(CustomerOrderStatus.class, description,
            new CustomerOrderException("Status inválidos: " + description + ". Os status válidos são: " + listOfAllowDescriptions(), 400));
    }

    public static String listOfAllowDescriptions() {
        return EnumUtils.listOfAllowDescriptions(CustomerOrderStatus.class);
    }

    public static String listOfAllowIds() {
        return EnumUtils.listOfAllowIds(CustomerOrderStatus.class);
    }
}
