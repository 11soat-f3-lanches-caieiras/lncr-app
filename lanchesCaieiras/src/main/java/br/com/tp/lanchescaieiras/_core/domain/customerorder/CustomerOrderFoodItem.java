package br.com.tp.lanchescaieiras._core.domain.customerorder;


import br.com.tp.lanchescaieiras._core.commons.dtos.customerorder.CustomerOrderFoodItemDTO;

public class CustomerOrderFoodItem {
    private Integer id;
    private Integer customerOrderId;
    private String name;
    private String description;
    private Double price;
    private String notes;

    public CustomerOrderFoodItem(Integer id, Integer customerOrderId, String name, String description, Double price, String notes) {
        this.id = id;
        this.customerOrderId = customerOrderId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.notes = notes;
    }

    public CustomerOrderFoodItem(CustomerOrderFoodItemDTO dto) {
        this.id = dto.getId();
        this.customerOrderId = dto.getOrderId();
        this.name = dto.getName();
        this.description = dto.getDescription();
        this.price = dto.getPrice();
        this.notes = dto.getNotes();
    }

    public CustomerOrderFoodItem(){}

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomerOrderId() {return customerOrderId;}
    public void setCustomerOrderId(Integer customerOrderId) {this.customerOrderId = customerOrderId;}

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

    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }

    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }
}
