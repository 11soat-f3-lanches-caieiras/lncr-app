package br.com.tp.lanchescaieiras._core.commons.utils;

import java.util.Map;

public class FoodItemImageRules {
    private String imageLocation;
    private Integer maxNumberOfImages;
    private Integer imageMaxSize;
    private Map<String, String> allowedExtentions;

    public FoodItemImageRules(String imageLocation, Integer maxNumberOfImages, Integer imageMaxSize, Map<String, String> allowedExtentions) {
        this.imageLocation = imageLocation;
        this.maxNumberOfImages = maxNumberOfImages;
        this.imageMaxSize = imageMaxSize;
        this.allowedExtentions = allowedExtentions;
    }

    public String getImageLocation() {
        return imageLocation;
    }

    public void setImageLocation(String imageLocation) {
        this.imageLocation = imageLocation;
    }

    public Integer getMaxNumberOfImages() {
        return maxNumberOfImages;
    }

    public void setMaxNumberOfImages(Integer maxNumberOfImages) {
        this.maxNumberOfImages = maxNumberOfImages;
    }

    public Integer getImageMaxSize() {
        return imageMaxSize;
    }

    public void setImageMaxSize(Integer imageMaxSize) {
        this.imageMaxSize = imageMaxSize;
    }

    public Map<String, String> getAllowedExtentions() {
        return allowedExtentions;
    }

    public void setAllowedExtentions(Map<String, String> allowedExtentions) {
        this.allowedExtentions = allowedExtentions;
    }
}
