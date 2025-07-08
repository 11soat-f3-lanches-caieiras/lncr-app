package br.com.tp.lanchescaieiras._external.integrations.fooditem;

import br.com.tp.lanchescaieiras._core.commons.dtos.customerorder.CustomerOrderFoodItemDTO;
import br.com.tp.lanchescaieiras._core.commons.dtos.fooditem.FoodItemDTO;
import br.com.tp.lanchescaieiras._external.commons.utils.IntegrationUtils;
import br.com.tp.lanchescaieiras._external.configs.IntegrationConfig;
import br.com.tp.lanchescaieiras._external.integrations.IntegrationException;
import br.com.tp.lanchescaieiras._external.integrations.IntegrationMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FoodItemIntegrationImpl implements FoodItemIntegration {

    public final IntegrationConfig integrationConfig;
    public final IntegrationMapper integrationMapper;

    public FoodItemIntegrationImpl(IntegrationConfig integrationConfig, IntegrationMapper integrationMapper) {
        this.integrationConfig = integrationConfig;
        this.integrationMapper = integrationMapper;
    }


    @Override
    public List<CustomerOrderFoodItemDTO> getFoodItemDetailList(List<Integer> foodItemIdList) {
        String ids = foodItemIdList.stream().map(String::valueOf).collect(Collectors.joining(","));
        String url = integrationConfig.getFoodItemsListUrl() + "/" + ids;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> getFoodItemDetails = new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        try {
            getFoodItemDetails = restTemplate.getForEntity(url, String.class);
        } catch (Exception e) {
            if (getFoodItemDetails.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw  new IntegrationException("Erro ao obter os dados dos items de alimentação ids: " + ids ,500);
            }
        }
        List<FoodItemDTO> foodItemDTOList = IntegrationUtils.getIntegrationContentList(getFoodItemDetails.getBody(),new TypeReference<List<FoodItemDTO>>() {});
        return foodItemDTOList.stream().map(integrationMapper::toCustomerOrderFoodItemDTO).toList();
    }


}
