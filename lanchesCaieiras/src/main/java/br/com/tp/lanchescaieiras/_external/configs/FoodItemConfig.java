package br.com.tp.lanchescaieiras._external.configs;

import br.com.tp.lanchescaieiras._core.adapters.fooditem.FoodItemControllerImpl;
import br.com.tp.lanchescaieiras._core.adapters.fooditem.FoodItemImageControllerImpl;
import br.com.tp.lanchescaieiras._core.adapters.fooditem.FoodItemMapper;
import br.com.tp.lanchescaieiras._external.datasources.postgres.fooditem.*;
import br.com.tp.lanchescaieiras._external.datasources.storage.fooditem.FoodItemImageStorageImpl;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@ConfigurationProperties(prefix = "lncr.food-item")
public class FoodItemConfig {
    private String locationPrefix;
    private Integer maxImages;

    @NestedConfigurationProperty
    private ImageConfig image;

    public String getLocationPrefix() {
        return locationPrefix;
    }

    public void setLocationPrefix(String locationPrefix) {
        this.locationPrefix = locationPrefix;
    }

    public Integer getMaxImages() {
        return maxImages;
    }

    public void setMaxImages(Integer maxImages) {
        this.maxImages = maxImages;
    }


    public ImageConfig getImage() {
        return image;
    }

    public void setImage(ImageConfig image) {
        this.image = image;
    }

    public static class ImageConfig {
        private String locationPrefix;
        private String directory;
        private Integer maxSize;
        private Map<String, String> allowedExtensions;

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

        public Integer getMaxSize() {
            return maxSize;
        }

        public void setMaxSize(Integer maxSize) {
            this.maxSize = maxSize;
        }

        public Map<String, String> getAllowedExtensions() {
            return allowedExtensions;
        }

        public void setAllowedExtensions(Map<String, String> allowedExtensions) {
            this.allowedExtensions = allowedExtensions;
        }
    }

    @Bean
    public FoodItemControllerImpl foodItemControllerImpl() {
        return new FoodItemControllerImpl();
    }

    @Bean
    public FoodItemImageControllerImpl foodItemImageController() {
        return new FoodItemImageControllerImpl();
    }

    @Bean
    public FoodItemMapper foodItemMapper() {
        return new FoodItemMapper();
    }

    @Bean
    public JpaFoodItemPostgresMapper jpaFoodItemPostgresMapper() {
        return new JpaFoodItemPostgresMapper();
    }

    @Bean
    public JpaFoodItemPostgresDatabaseImpl jpaFoodItemPostgresDatabaseImpl(
            JpaFoodItemPostgresReposity jpaFoodItemPostgresReposity,
            FoodItemImageStorageImpl foodItemImageStorage,
            JpaFoodItemPostgresMapper jpaFoodItemPostgresMapper,
            FoodItemConfig foodItemConfig) {
        return new JpaFoodItemPostgresDatabaseImpl(jpaFoodItemPostgresReposity, foodItemImageStorage, jpaFoodItemPostgresMapper, foodItemConfig);
    }

    @Bean
    public JpaFoodItemImagePostgresDatabaseImpl jpaFoodItemImagePostgresDatabaseImpl(
            JpaFoodItemImagePostgresRepository jpaFoodItemImagePostgresRepository,
            FoodItemImageStorageImpl foodItemImageStorage,
            JpaFoodItemPostgresMapper foodItemMapper) {
        return new JpaFoodItemImagePostgresDatabaseImpl(jpaFoodItemImagePostgresRepository, foodItemImageStorage, foodItemMapper);
    }


    @Bean
    public FoodItemImageStorageImpl foodItemImageStorageImpl(FoodItemConfig foodItemConfig) {
        return new FoodItemImageStorageImpl(foodItemConfig);
    }

}
