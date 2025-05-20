package br.com.tp.lanchescaieiras.customerorder.adapters.outbound.integrations;

import br.com.tp.lanchescaieiras.customerorder.domain.CustomerOrderCustomer;

public interface CustomerIntegration {

    CustomerOrderCustomer getCustomerOrderCustomerDetails(Integer customerId);

}
