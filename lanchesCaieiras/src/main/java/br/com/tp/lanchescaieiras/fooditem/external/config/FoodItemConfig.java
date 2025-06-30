package br.com.tp.lanchescaieiras.fooditem.external.config;

import br.com.tp.lanchescaieiras.fooditem.external.datasources.postgres.JpaFoodItemImagePostgresDatabaseImpl;
import br.com.tp.lanchescaieiras.fooditem.external.datasources.postgres.JpaFoodItemImagePostgresRepository;
import br.com.tp.lanchescaieiras.fooditem.external.datasources.postgres.JpaFoodItemPostgresDatabaseImpl;
import br.com.tp.lanchescaieiras.fooditem.external.datasources.postgres.JpaFoodItemPostgresMapper;
import br.com.tp.lanchescaieiras.fooditem.external.datasources.postgres.JpaFoodItemPostgresReposity;
import br.com.tp.lanchescaieiras.fooditem.external.storage.FoodItemImageStorageImpl;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.context.annotation.Bean;

import java.util.Map;

import br.com.tp.lanchescaieiras.fooditem.adapters.FoodItemControllerImpl;
import br.com.tp.lanchescaieiras.fooditem.mappers.FoodItemMapper;

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
        private Map<String, String> extensions;

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

        public Map<String, String> getExtensions() {
            return extensions;
        }

        public void setExtensions(Map<String, String> extensions) {
            this.extensions = extensions;
        }
    }

    @Bean
    public FoodItemControllerImpl foodItemControllerImpl() {
        return new FoodItemControllerImpl();
    }

    @Bean
    public FoodItemMapper foodItemMapper() {return new FoodItemMapper();}

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
            JpaFoodItemPostgresMapper foodItemMapper,
            FoodItemConfig foodItemConfig) {
        return new JpaFoodItemImagePostgresDatabaseImpl(jpaFoodItemImagePostgresRepository, foodItemImageStorage, foodItemMapper, foodItemConfig);
    }

    @Bean
    public FoodItemImageStorageImpl foodItemImageStorageImpl(FoodItemConfig foodItemConfig) {
        return new FoodItemImageStorageImpl(foodItemConfig);
    }

}
