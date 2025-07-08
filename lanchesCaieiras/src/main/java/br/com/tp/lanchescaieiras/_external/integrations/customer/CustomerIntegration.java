package br.com.tp.lanchescaieiras._external.integrations.customer;

import br.com.tp.lanchescaieiras._core.commons.dtos.customerorder.CustomerOrderCustomerDTO;

import java.util.List;

public interface CustomerIntegration {

    List<CustomerOrderCustomerDTO> getCustomerDetailsList(List<Integer> customerIdList);

    CustomerOrderCustomerDTO getCustomerDetails(Integer customerId);
}
