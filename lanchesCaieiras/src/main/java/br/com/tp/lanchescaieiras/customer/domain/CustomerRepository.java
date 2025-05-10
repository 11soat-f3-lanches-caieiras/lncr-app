package br.com.tp.lanchescaieiras.customer.domain;

import java.util.Optional;

public interface CustomerRepository {

    Optional<Customer> findById(Integer id);
    /*Customer findByDocumentNumber(String documentNumber);
    List<Customer> findAll();
    Customer updateById(Integer id, Customer customer);
    Customer updateByDocumentNumber(String documentNumber, Customer customer);*/Customer save(Customer customer);
    void deletebyId(Integer id);
}
/*#Customer findByDocumentNumber(String documentNumber);
    #List<Customer> findAll();*/
    /*#Customer updateById(Integer id, Customer customer);
    #Customer updateByDocumentNumber(String documentNumber, Customer customer);
}*/

/*#Customer findByDocumentNumber(String documentNumber);
    #List<Customer> findAll();*/
    /*#Customer updateById(Integer id, Customer customer);
    #Customer updateByDocumentNumber(String documentNumber, Customer customer);*/