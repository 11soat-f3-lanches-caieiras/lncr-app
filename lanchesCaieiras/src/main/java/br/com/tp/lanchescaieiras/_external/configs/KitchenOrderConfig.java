package br.com.tp.lanchescaieiras._external.configs;

import br.com.tp.lanchescaieiras._core.adapters.kitchenorder.KitchenOrderControllerImpl;
import br.com.tp.lanchescaieiras._core.commons.interfaces.kitchenorder.KitchenOrderDatabase;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "lncr.kitchen-order")
public class KitchenOrderConfig {

    private String locationPrefix;

    public String getLocationPrefix() {
        return locationPrefix;
    }

    public void setLocationPrefix(String locationPrefix) {
        this.locationPrefix = locationPrefix;
    }

    @Bean
    KitchenOrderControllerImpl kitchenOrderControllerImpl(KitchenOrderDatabase kitchenOrderDatabase){
        return new KitchenOrderControllerImpl(kitchenOrderDatabase);
    }

}
