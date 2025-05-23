package br.com.tp.lanchescaieiras.customerorder.domain;

import br.com.tp.lanchescaieiras.customerorder.infraestructure.exceptions.CustomerOrderException;
import jakarta.persistence.Id;

public enum CustomerOrderStatus {
    CHECKOUT(1, "Checkout"),
    RECEIVED(2, "Received"),
    PREPARING(3, "Preparing"),
    READY(4, "Ready"),
    FINISHED(5, "Finished");

    @Id
    private final int id;
    private final String description;

    CustomerOrderStatus(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public static CustomerOrderStatus fromId(int id) {
        for (CustomerOrderStatus status : values()) {
            if (status.id == id) {
                return status;
            }
        }
        throw new CustomerOrderException("Id do status inválido: " + id + ". Os ids de status válidos são: " + CustomerOrderStatus.listOfAllowIds(),400);
    }

    public static CustomerOrderStatus fromDescription(String description) {
        for (CustomerOrderStatus status : values()) {
            if (status.description.equalsIgnoreCase(description)) {
                return status;
            }
        }
        throw new CustomerOrderException("Status inválidos: " + description + ". Os status válidos são: " + CustomerOrderStatus.listOfAllowDescriptions(),400);
    }

    public static String listOfAllowDescriptions(){
        String listOfAllowDescriptions = new String();
        for (CustomerOrderStatus status : values()) {
            if (listOfAllowDescriptions.length() > 0) {
                listOfAllowDescriptions = listOfAllowDescriptions + ", ";
            }
            listOfAllowDescriptions = listOfAllowDescriptions + status.getDescription();
        }
        return listOfAllowDescriptions;
    }

    public static String listOfAllowIds(){
        String listOfAllowIds = new String();

        for (CustomerOrderStatus status : values()) {
            if (listOfAllowIds.length() > 0) {
                listOfAllowIds = listOfAllowIds + ",";
            }
            listOfAllowIds = listOfAllowIds + status.getId();
        }
        return listOfAllowIds;
    }



}
