package br.com.tp.lanchescaieiras.customerorder.application.usecases;

import br.com.tp.lanchescaieiras.customerorder.domain.CustomerOrder;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface CustomerOrderUseCases {
    public CustomerOrder createCustomerOrder(CustomerOrder customerOrder);

    public CustomerOrder findById(Integer id, Boolean includeFoodItems) ;

    public List<CustomerOrder> findByStatus(String status, Boolean includeFoodItems);
}
