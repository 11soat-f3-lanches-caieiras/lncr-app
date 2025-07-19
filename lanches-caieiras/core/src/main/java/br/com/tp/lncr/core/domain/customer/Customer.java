package br.com.tp.lncr.core.domain.customer;

import br.com.tp.lncr.core.commons.dtos.customer.CustomerDTO;

public class Customer {
    public Integer id;
    public CustomerCPF documentNumber;
    public String name;
    public CustomerEmail email;

    public Customer() {
    }

    public Customer(CustomerDTO customerDTO) {
        if (customerDTO != null) {
            this.id = customerDTO.getId();
            this.setDocumentNumber(customerDTO.getDocumentNumber());
            this.name = customerDTO.getName();
            this.setEmail(customerDTO.getEmail());
        }
    }

    public Customer(Integer id, String documentNumber, String name, String email) {
        this.id = id;
        this.documentNumber = new CustomerCPF(documentNumber);
        this.name = name;
        this.email = new CustomerEmail(email);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDocumentNumber() {
        return documentNumber.getValue();
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = new CustomerCPF(documentNumber);
    }

    public boolean documentNumberIsValid() {
        return this.documentNumber.documentNumberIsValid(this.documentNumber.getValue());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email.getValue();
    }

    public void setEmail(String email) {
        this.email = new CustomerEmail(email);
    }

    public boolean emailIsValid() {
        return this.email.emailIsValid(this.email.getValue());
    }

    @Override
    public String toString() {
        return "Customer{" + "id=" + id + ", documentNumber='" + documentNumber + '\'' + ", name='" + name + '\'' + ", email='" + email + '\'' + '}';
    }
}


