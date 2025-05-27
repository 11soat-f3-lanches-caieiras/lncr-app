package br.com.tp.lanchescaieiras.payments.mercadopago.domain;


public enum PaymentStatus {
    CHARGED(1, "Charged"),
    PAID(2, "Paid");

    private final int id;
    private final String description;

    PaymentStatus(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public static PaymentStatus fromId(int code) {
        for (PaymentStatus status : PaymentStatus.values()) {
            if (status.getId() == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid code: " + code);
    }

    public static PaymentStatus fromDescription(String description) {
        for (PaymentStatus status : PaymentStatus.values()) {
            if (status.getDescription().equalsIgnoreCase(description)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid description: " + description);
    }


}
