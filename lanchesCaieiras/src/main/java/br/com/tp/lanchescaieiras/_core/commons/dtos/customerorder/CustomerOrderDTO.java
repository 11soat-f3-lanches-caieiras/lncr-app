package br.com.tp.lanchescaieiras._core.commons.dtos.customerorder;

import java.time.LocalDateTime;
import java.util.List;

public class CustomerOrderDTO {
    private Integer id;
    private String status;
    private Double totalCost;
    private LocalDateTime _created;
    private CustomerOrderCustomerDTO customer;
    private List<CustomerOrderFoodItemDTO> foodItems;

    public CustomerOrderDTO() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Double getTotalCost() { return totalCost; }
    public void setTotalCost(Double totalCost) { this.totalCost = totalCost; }

    public LocalDateTime get_created() { return _created; }
    public void set_created(LocalDateTime _created) { this._created = _created; }

    public CustomerOrderCustomerDTO getCustomer() {return customer;}
    public void setCustomer(CustomerOrderCustomerDTO customer) {this.customer = customer;}

    public List<CustomerOrderFoodItemDTO> getFoodItems() { return foodItems; }
    public void setFoodItems(List<CustomerOrderFoodItemDTO> foodItems) { this.foodItems = foodItems; }
}

