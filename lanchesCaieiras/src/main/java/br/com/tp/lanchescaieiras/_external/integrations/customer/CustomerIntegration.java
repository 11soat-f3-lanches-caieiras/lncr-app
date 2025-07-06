package br.com.tp.lanchescaieiras._external.integrations.customer;

import br.com.tp.lanchescaieiras._core.commons.dtos.customerorder.CustomerOrderCustomerDTO;

public interface CustomerIntegration {

    CustomerOrderCustomerDTO getCustomerDetails(Integer customerId);

}
