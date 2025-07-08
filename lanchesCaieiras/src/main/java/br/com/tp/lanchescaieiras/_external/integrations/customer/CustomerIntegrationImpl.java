package br.com.tp.lanchescaieiras._external.integrations.customer;

import br.com.tp.lanchescaieiras._core.commons.dtos.customer.CustomerDTO;
import br.com.tp.lanchescaieiras._core.commons.dtos.customerorder.CustomerOrderCustomerDTO;
import br.com.tp.lanchescaieiras._external.commons.utils.IntegrationUtils;
import br.com.tp.lanchescaieiras._external.configs.IntegrationConfig;
import br.com.tp.lanchescaieiras._external.integrations.IntegrationException;
import br.com.tp.lanchescaieiras._external.integrations.IntegrationMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerIntegrationImpl implements CustomerIntegration {

    private final IntegrationConfig integrationConfig;
    private final IntegrationMapper integrationMapper;

    public CustomerIntegrationImpl(IntegrationConfig integrationConfig, IntegrationMapper integrationMapper) {
        this.integrationConfig = integrationConfig;
        this.integrationMapper = integrationMapper;
    }

    @Override
    public List<CustomerOrderCustomerDTO> getCustomerDetailsList(List<Integer> customerIdList) {
        String ids = customerIdList.stream().map(String::valueOf).collect(Collectors.joining(","));
        String url = integrationConfig.getCustomersListUrl() + "/" + ids;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> getCustomerDetails = new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        try {
            getCustomerDetails = restTemplate.getForEntity(url, String.class);
        } catch (Exception e) {
            if (e instanceof HttpClientErrorException.NotFound) {
                throw new IntegrationException("Não encontrado clientes com ids: " + ids, 404);
            }
        }
        List<CustomerDTO> customerDTOList = IntegrationUtils.getIntegrationContentList(getCustomerDetails.getBody(),new TypeReference<List<CustomerDTO>>() {});
        return customerDTOList.stream().map(integrationMapper::toCustomerOrderCustomerDTO).toList();
    }

    @Override
    public CustomerOrderCustomerDTO getCustomerDetails(Integer customerId) {
        String url = integrationConfig.getCustomersUrl()+ "/" + customerId;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> getCustomerDetails = new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        try {
            getCustomerDetails = restTemplate.getForEntity(url, String.class);
        } catch (Exception e) {
            if (e instanceof HttpClientErrorException.NotFound) {
                throw new IntegrationException("Não encontrado cliente com id: " + customerId, 404);
            }
        }
        CustomerDTO customerDTO = IntegrationUtils.getIntegrationContent(getCustomerDetails.getBody(),CustomerDTO.class);
        return this.integrationMapper.toCustomerOrderCustomerDTO(customerDTO);
    }
}
