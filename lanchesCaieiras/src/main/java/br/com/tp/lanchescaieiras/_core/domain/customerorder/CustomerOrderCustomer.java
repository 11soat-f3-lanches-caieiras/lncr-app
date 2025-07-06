package br.com.tp.lanchescaieiras._core.domain.customerorder;

import br.com.tp.lanchescaieiras._core.commons.dtos.customerorder.CustomerOrderCustomerDTO;

public class CustomerOrderCustomer {
    Integer id;
    String name;

    public CustomerOrderCustomer() {
    }

    public CustomerOrderCustomer(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public CustomerOrderCustomer(CustomerOrderCustomerDTO dto) {
        this.id = dto.getId();
        this.name = dto.getName();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
