package br.com.tp.lanchescaieiras._core.domain.customerorder;

import br.com.tp.lanchescaieiras._core.commons.dtos.customerorder.CustomerOrderDTO;
import br.com.tp.lanchescaieiras._core.domain.exceptions.CustomerOrderException;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.List;

public class CustomerOrder {
    private Integer id;
    private String status;
    private Double totalCost;
    private LocalDateTime _created;
    private CustomerOrderCustomer customer;
    private List<CustomerOrderFoodItem> foodItems;

    public CustomerOrder(Integer id, CustomerOrderStatus status, CustomerOrderCustomer customer, List<CustomerOrderFoodItem> foodItems, Double totalCost) {
        this.id = id;
        this.status = fromCustomerOrderStatus(status);
        this.customer = customer;
        this.foodItems = foodItems;
        this.totalCost = totalCost;
        this._created = LocalDateTime.now();
        setTotalCost();
    }

    public CustomerOrder(CustomerOrderDTO dto) {
        this.id = dto.getId();
        this.status = dto.getStatus();
        this.totalCost = dto.getTotalCost();
        this._created = dto.get_created();
        this.customer = null;
        if (dto.getCustomer() != null) {
            this.customer = new CustomerOrderCustomer(dto.getCustomer());
        }

        //Regra de negócio que pedido deve ter pelo menos um item no pedido
        if (dto.getFoodItems()==null || dto.getFoodItems().isEmpty()) {
            throw new CustomerOrderException("Pedido do cliente deve conter pelo menos 1 item",400);
        }

        this.foodItems = dto.getFoodItems().stream().map(CustomerOrderFoodItem::new).toList();
        setTotalCost();
    }

    public CustomerOrder() {

    }

    public void setTotalCost(Double totalCost){
        this.totalCost = totalCost;
    }

    public void setTotalCost() {
        this.totalCost = 0.0;
        if (this.foodItems != null) {
            for (CustomerOrderFoodItem foodItem : foodItems) {
                this.totalCost += foodItem.getPrice() == null ? 0.00 : foodItem.getPrice();
            }
        }
        this.totalCost = Double.parseDouble(new DecimalFormat("#.00").format(this.totalCost).replace(",", "."));
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public CustomerOrderCustomer getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerOrderCustomer customer) {
        this.customer = customer;
    }

    public List<CustomerOrderFoodItem> getFoodItems() {
        return foodItems;
    }

    public void setFoodItems(List<CustomerOrderFoodItem> foodItems) {
        this.foodItems = foodItems;
        if (this.foodItems != null) {
            setTotalCost();
        }
    }

    public void setStatus(CustomerOrderStatus status) {
        this.status = fromCustomerOrderStatus(status);
    }

    public String fromCustomerOrderStatus(CustomerOrderStatus status) {
        return status.getDescription();
    }

    public LocalDateTime get_created() {
        return _created;
    }

    public void set_created(LocalDateTime _created) {
        this._created = _created;
    }

}
