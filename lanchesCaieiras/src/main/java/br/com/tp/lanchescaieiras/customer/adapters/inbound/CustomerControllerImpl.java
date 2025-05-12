package br.com.tp.lanchescaieiras.customer.adapters.inbound;

import br.com.tp.lanchescaieiras.customer.application.services.usecases.CustomerUseCases;
import br.com.tp.lanchescaieiras.customer.domain.Customer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/customers")
public class CustomerControllerImpl implements CustomerUseCases {

    @Override
    @PostMapping
    public Customer createCustomer(Customer customer) {
        return null;
    }

    @Override
    public Optional<Customer> getCustomerById(Integer id) {
        return Optional.empty();
    }

    @Override
    public List<Customer> getAllCustomers() {
        return List.of();
    }

    @Override
    public void updateCustomer(Integer id, Customer customer) {

    }

    @Override
    public void deleteCustomer(Integer id) {

    }
}
