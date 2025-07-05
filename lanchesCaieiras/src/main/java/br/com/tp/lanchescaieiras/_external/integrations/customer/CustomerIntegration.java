package br.com.tp.lanchescaieiras._external.integrations.customer;

import br.com.tp.lanchescaieiras._core.domain.customerorder.CustomerOrderCustomer;

public interface CustomerIntegration {

    CustomerOrderCustomer getCustomerOrderCustomerDetails(Integer customerId);

}
