package br.com.tp.lanchescaieiras._external.integrations.customer;

import br.com.tp.lanchescaieiras._core.commons.dtos.customer.CustomerDTO;
import br.com.tp.lanchescaieiras._core.commons.dtos.customerorder.CustomerOrderCustomerDTO;
import br.com.tp.lanchescaieiras._core.domain.exceptions.FoodItemException;
import br.com.tp.lanchescaieiras._external.configs.IntegrationConfig;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class CustomerIntegrationImpl implements CustomerIntegration {

    private final IntegrationConfig integrationConfig;

    public CustomerIntegrationImpl(IntegrationConfig integrationConfig) {
        this.integrationConfig = integrationConfig;
    }

    @Override
    public CustomerOrderCustomerDTO getCustomerDetails(Integer customerId) {
        String url = integrationConfig.getCustomersUrl() + "/" + customerId;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<CustomerDTO> getCustomerDetails = new ResponseEntity<CustomerDTO>(HttpStatus.NOT_FOUND);
        try {
            getCustomerDetails = restTemplate.getForEntity(url, CustomerDTO.class);
        } catch (Exception e) {
            if (e instanceof HttpClientErrorException.NotFound) {
                throw new FoodItemException("Cliente id: " + customerId + " não encontrado", 404);
            }
        }
        return customerDtoToCustumerInCustomerOrder(getCustomerDetails.getBody());
    }

    private CustomerOrderCustomerDTO customerDtoToCustumerInCustomerOrder(CustomerDTO customerDTO){
        if (customerDTO == null) return null;
        return  new CustomerOrderCustomerDTO(customerDTO.getId(), customerDTO.getName());
    }



}
