package br.com.tp.lanchescaieiras._core.commons.dtos.kitchenorder;

public class KitchenOrderFoodItemDTO {
    private Integer id;
    private Integer kitchenOrderId;
    private String name;
    private String description;
    private String notes;

    public KitchenOrderFoodItemDTO() {}

    public KitchenOrderFoodItemDTO(Integer id, Integer kitchenOrderId, String name, String description, String notes) {
        this.id = id;
        this.kitchenOrderId = kitchenOrderId;
        this.name = name;
        this.description = description;
        this.notes = notes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getKitchenOrderId() {
        return kitchenOrderId;
    }

    public void setKitchenOrderId(Integer kitchenOrderId) {
        this.kitchenOrderId = kitchenOrderId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}

