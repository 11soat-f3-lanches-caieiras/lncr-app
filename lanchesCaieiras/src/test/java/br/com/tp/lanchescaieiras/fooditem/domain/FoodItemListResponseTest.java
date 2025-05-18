package br.com.tp.lanchescaieiras.fooditem.domain;

import br.com.tp.lanchescaieiras.commons.domain.ResponseMetada;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class FoodItemListResponseTest {

    @Test
    void deveCriarRespostaComListaDeItensERespostaMetadata() {
        ResponseMetada responseMetada = new ResponseMetada("200", "Sucesso");
        FoodItem foodItem = new FoodItem(1, "Sanduíche Natural", "Sanduíche com peito de peru", 15.0, FoodItemCategory.SANDWICH, null);
        FoodItemListResponse response = new FoodItemListResponse(responseMetada, List.of(foodItem));

        Assertions.assertNotNull(response.getResponseMetada());
        Assertions.assertNotNull(response.getFoodItems());
        Assertions.assertEquals("200", response.getResponseMetada().getCode());
        Assertions.assertEquals(1, response.getFoodItems().size());
        Assertions.assertEquals("Sanduíche Natural", response.getFoodItems().get(0).getName());
    }

    @Test
    void deveCriarRespostaComListaDeItensSemRespostaMetadata() {
        FoodItem foodItem = new FoodItem(2, "Suco de Laranja", "Suco natural de laranja", 8.0, FoodItemCategory.BEVERAGE, null);
        FoodItemListResponse response = new FoodItemListResponse(List.of(foodItem));

        Assertions.assertNotNull(response.getResponseMetada());
        Assertions.assertNotNull(response.getFoodItems());
        Assertions.assertEquals(1, response.getFoodItems().size());
        Assertions.assertEquals("Suco de Laranja", response.getFoodItems().get(0).getName());
    }

    @Test
    void deveCriarRespostaVazia() {
        FoodItemListResponse response = new FoodItemListResponse();

        Assertions.assertNull(response.getResponseMetada());
        Assertions.assertNull(response.getFoodItems());
    }

    @Test
    void deveAtualizarRespostaMetadataNaResposta() {
        FoodItemListResponse response = new FoodItemListResponse();
        ResponseMetada responseMetada = new ResponseMetada("404", "Não encontrado");
        response.setResponseMetada(responseMetada);

        Assertions.assertNotNull(response.getResponseMetada());
        Assertions.assertEquals("404", response.getResponseMetada().getCode());
    }

    @Test
    void deveAtualizarListaDeItensNaResposta() {
        FoodItemListResponse response = new FoodItemListResponse();
        FoodItem foodItem = new FoodItem(3, "Pudim", "Pudim de leite condensado", 10.0, FoodItemCategory.DESSERT, null);
        response.setFoodItems(List.of(foodItem));

        Assertions.assertNotNull(response.getFoodItems());
        Assertions.assertEquals(1, response.getFoodItems().size());
        Assertions.assertEquals("Pudim", response.getFoodItems().get(0).getName());
    }
}