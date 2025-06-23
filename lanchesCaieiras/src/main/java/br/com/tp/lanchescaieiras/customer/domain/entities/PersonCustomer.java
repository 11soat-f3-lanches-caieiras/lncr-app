package br.com.tp.lanchescaieiras.customer.domain.entities;

public class PersonCustomer {
    Customer<CustomerCPF> customer;

    public PersonCustomer() {
    }

    public PersonCustomer(Customer<CustomerCPF> customer) {
        this.customer = customer;
    }

    public Customer<CustomerCPF> getCustomer() {
        return customer;
    }

    public void setCustomer(Customer<CustomerCPF> customer) {
        this.customer = customer;
    }
}
