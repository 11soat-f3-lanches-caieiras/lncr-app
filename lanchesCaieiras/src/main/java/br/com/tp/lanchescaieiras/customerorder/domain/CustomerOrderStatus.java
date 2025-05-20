package br.com.tp.lanchescaieiras.customerorder.domain;

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
        throw new IllegalArgumentException("Invalid CustomerOrderStatus id: " + id);
    }

    public static CustomerOrderStatus fromDescription(String description) {
        for (CustomerOrderStatus status : values()) {
            if (status.description.equalsIgnoreCase(description)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid CustomerOrderStatus description: " + description);
    }



}
