package br.com.tp.lanchescaieiras.kitchenorder.domain;

import br.com.tp.lanchescaieiras.kitchenorder.infraestructure.exceptions.KitchenOrderException;
import jakarta.persistence.Id;

public enum KitchenOrderStatus {
    RECEIVED(1, "Received"),
    PREPARING(2, "Preparing"),
    READY(3, "Ready"),
    FINISHED(4, "Finished");

    @Id
    private final int id;
    private final String description;

    KitchenOrderStatus(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public static KitchenOrderStatus fromId(int id) {
        for (KitchenOrderStatus status : values()) {
            if (status.id == id) {
                return status;
            }
        }
        throw new KitchenOrderException("Id do status inválido: " + id + ". Os ids de status válidos são: " + KitchenOrderStatus.listOfAllowIds(), 400);
    }

    public static KitchenOrderStatus fromDescription(String description) {
        for (KitchenOrderStatus status : values()) {
            if (status.description.equalsIgnoreCase(description)) {
                return status;
            }
        }
        throw new KitchenOrderException("Status inválidos: " + description + ". Os status válidos são: " + KitchenOrderStatus.listOfAllowDescriptions(), 400);
    }

    public static String listOfAllowDescriptions() {
        String listOfAllowDescriptions = "";
        for (KitchenOrderStatus status : values()) {
            if (!listOfAllowDescriptions.isEmpty()) {
                listOfAllowDescriptions = listOfAllowDescriptions + ", ";
            }
            listOfAllowDescriptions = listOfAllowDescriptions + status.getDescription();
        }
        return listOfAllowDescriptions;
    }

    public static String listOfAllowIds() {
        String listOfAllowIds = "";

        for (KitchenOrderStatus status : values()) {
            if (!listOfAllowIds.isEmpty()) {
                listOfAllowIds = listOfAllowIds + ",";
            }
            listOfAllowIds = listOfAllowIds + status.getId();
        }
        return listOfAllowIds;
    }


}
