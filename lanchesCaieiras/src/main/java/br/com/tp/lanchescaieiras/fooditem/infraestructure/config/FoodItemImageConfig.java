package br.com.tp.lanchescaieiras.fooditem.infraestructure.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@ConfigurationProperties(prefix = "lncr.food-item-image")
public class FoodItemImageConfig {

    private Integer maxSize;
    private String locationPrefix;
    private String directory;


    public void setMaxSize(Integer maxSize) {
        this.maxSize = maxSize;
    }

    private Map<String, String> extensions;

   public Map<String, String> getExtensions() {
        return extensions;
    }

    public void setExtensions(Map<String, String> extensions) {
        this.extensions = extensions;
    }

    public Integer getMaxSize() {
        return maxSize;
    }

    public String getLocationPrefix() {
        return locationPrefix;
    }

    public void setLocationPrefix(String locationPrefix) {
        this.locationPrefix = locationPrefix;
    }

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }
}
