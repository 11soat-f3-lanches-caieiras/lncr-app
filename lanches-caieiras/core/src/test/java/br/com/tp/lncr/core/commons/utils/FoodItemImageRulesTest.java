package br.com.tp.lncr.core.commons.utils;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FoodItemImageRulesTest {
    @Test
    void testConstructorAndGetters() {
        Map<String, String> allowed = new HashMap<>();
        allowed.put("jpg", "image/jpeg");
        allowed.put("png", "image/png");
        FoodItemImageRules rules = new FoodItemImageRules("/img", 5, 1024, allowed);
        assertEquals("/img", rules.getImageLocation());
        assertEquals(5, rules.getMaxNumberOfImages());
        assertEquals(1024, rules.getImageMaxSize());
        assertEquals(allowed, rules.getAllowedExtentions());
    }

    @Test
    void testSetters() {
        FoodItemImageRules rules = new FoodItemImageRules("", 0, 0, null);
        rules.setImageLocation("/new");
        rules.setMaxNumberOfImages(10);
        rules.setImageMaxSize(2048);
        Map<String, String> allowed = new HashMap<>();
        allowed.put("gif", "image/gif");
        rules.setAllowedExtentions(allowed);
        assertEquals("/new", rules.getImageLocation());
        assertEquals(10, rules.getMaxNumberOfImages());
        assertEquals(2048, rules.getImageMaxSize());
        assertEquals(allowed, rules.getAllowedExtentions());
    }
}

