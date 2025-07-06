package br.com.tp.lanchescaieiras._core.commons.dtos.customerorder;

public class CustomerOrderFoodItemDTO {
    private Integer id;
    private Integer orderId;
    private String name;
    private String description;
    private Double price;
    private String notes;

    public CustomerOrderFoodItemDTO() {}

    public CustomerOrderFoodItemDTO(Integer id, Integer orderId, String name, String description, Double price, String notes) {
        this.id = id;
        this.orderId = orderId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.notes = notes;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}

