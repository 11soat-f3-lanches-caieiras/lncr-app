package br.com.tp.lanchescaieiras.fooditem.domain;

import br.com.tp.lanchescaieiras.commons.domain.ResponseMetada;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

        class FoodItemResponseTest {

            @Test
            void deveCriarRespostaComItemERespostaMetadata() {
                ResponseMetada responseMetada = new ResponseMetada("200", "Sucesso");
                FoodItem foodItem = new FoodItem(1, "Sanduíche Natural", "Sanduíche com peito de peru", 15.0, FoodItemCategory.SANDWICH, null);
                FoodItemResponse response = new FoodItemResponse(responseMetada, foodItem);

                Assertions.assertNotNull(response.get_response());
                Assertions.assertNotNull(response.getFoodItem());
                //Assertions.assertEquals("200", response.get_response().getCode());
                Assertions.assertEquals("Sanduíche Natural", response.getFoodItem().getName());
            }

            @Test
            void deveCriarRespostaComItemSemRespostaMetadata() {
                FoodItem foodItem = new FoodItem(2, "Suco de Laranja", "Suco natural de laranja", 8.0, FoodItemCategory.DRINK, null);
                FoodItemResponse response = new FoodItemResponse(foodItem);

                Assertions.assertNotNull(response.get_response());
                Assertions.assertNotNull(response.getFoodItem());
                Assertions.assertEquals("Suco de Laranja", response.getFoodItem().getName());
            }

            @Test
            void deveCriarRespostaVazia() {
                FoodItemResponse response = new FoodItemResponse();

                Assertions.assertNull(response.get_response());
                Assertions.assertNull(response.getFoodItem());
            }

            @Test
            void deveAtualizarRespostaMetadataNaResposta() {
                FoodItemResponse response = new FoodItemResponse();
                ResponseMetada responseMetada = new ResponseMetada("404", "Não encontrado");
                response.set_response(responseMetada);

                Assertions.assertNotNull(response.get_response());
               // Assertions.assertEquals("404", response.get_response().getCode());
            }

            @Test
            void deveAtualizarItemNaResposta() {
                FoodItemResponse response = new FoodItemResponse();
                FoodItem foodItem = new FoodItem(3, "Pudim", "Pudim de leite condensado", 10.0, FoodItemCategory.DESSERT, null);
                response.setFoodItem(foodItem);

                Assertions.assertNotNull(response.getFoodItem());
                Assertions.assertEquals("Pudim", response.getFoodItem().getName());
            }
        }