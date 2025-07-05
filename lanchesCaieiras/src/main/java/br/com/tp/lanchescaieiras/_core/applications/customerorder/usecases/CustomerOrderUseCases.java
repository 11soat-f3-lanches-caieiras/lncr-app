package br.com.tp.lanchescaieiras._core.applications.customerorder.usecases;

import br.com.tp.lanchescaieiras._core.domain.customerorder.CustomerOrder;

import java.util.List;

public interface CustomerOrderUseCases {
    CustomerOrder createCustomerOrder(CustomerOrder customerOrder);

    CustomerOrder findById(Integer id, Boolean includeFoodItems);

    List<CustomerOrder> findByStatus(String status, Boolean includeFoodItems);

    CustomerOrder updateStatusById(Integer customerOrderId, String newStatus, Boolean forceUpdate);
}
