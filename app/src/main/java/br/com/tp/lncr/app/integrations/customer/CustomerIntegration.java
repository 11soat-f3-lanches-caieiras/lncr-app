package br.com.tp.lncr.app.integrations.customer;

import br.com.tp.lncr.core.commons.dtos.customerorder.CustomerOrderCustomerDTO;

import java.util.List;

public interface CustomerIntegration {

    CustomerOrderCustomerDTO getCustomerDetails(Integer customerId);

    List<CustomerOrderCustomerDTO> getCustomerDetailsList(List<Integer> customerIdList);
}
