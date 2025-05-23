package br.com.tp.lanchescaieiras.customerorder.application.usecases;

import br.com.tp.lanchescaieiras.customerorder.domain.CustomerOrder;

import java.util.List;

public interface CustomerOrderUseCases {
    public CustomerOrder createCustomerOrder(CustomerOrder customerOrder);

    public CustomerOrder findById(Integer id, Boolean includeFoodItems) ;

    public List<CustomerOrder> findByStatus(String status, Boolean includeFoodItems);

    CustomerOrder updateStatusById(Integer customerOrderId, String newStatus, Boolean forceUpdate);
}
