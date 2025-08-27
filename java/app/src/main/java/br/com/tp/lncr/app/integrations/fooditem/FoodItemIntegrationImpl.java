package br.com.tp.lncr.app.integrations.fooditem;

import br.com.tp.lncr.app.commons.utils.IntegrationUtil;
import br.com.tp.lncr.app.configs.IntegrationConfig;
import br.com.tp.lncr.app.integrations.IntegrationMapper;
import br.com.tp.lncr.core.commons.dtos.customerorder.CustomerOrderFoodItemDTO;
import br.com.tp.lncr.core.commons.dtos.fooditem.FoodItemDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
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
        List<FoodItemDTO> foodItemDTOList = IntegrationUtil.getForObject(url, new TypeReference<List<FoodItemDTO>>() {});
        return foodItemDTOList.stream().map(integrationMapper::toCustomerOrderFoodItemDTO).toList();
    }

}
