package br.com.tp.lanchescaieiras.customer.domain;

public interface CustomerService {
    Customer findById(Integer id);
    Customer save(Customer customer);
    void deleteById(Integer id);

}
