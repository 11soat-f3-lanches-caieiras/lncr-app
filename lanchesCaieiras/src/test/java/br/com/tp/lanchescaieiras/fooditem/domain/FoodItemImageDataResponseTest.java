package br.com.tp.lanchescaieiras.fooditem.domain;

import br.com.tp.lanchescaieiras.commons.domain.ResponseMetada;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FoodItemImageDataResponseTest {

    @Test
    void deveCriarRespostaComImagemERespostaMetadata() {
        ResponseMetada responseMetada = new ResponseMetada("200", "Sucesso");
        FoodItemImage foodItemImage = new FoodItemImage("dadosBase64", "imagem.jpg", "jpg");
        FoodItemImageDataResponse response = new FoodItemImageDataResponse(responseMetada, foodItemImage);

        Assertions.assertNotNull(response.get_response());
        Assertions.assertNotNull(response.get_data());
        Assertions.assertEquals("200", response.get_response().getCode());
        Assertions.assertEquals("imagem.jpg", response.get_data().getFileName());
    }

    @Test
    void deveCriarRespostaComImagemSemRespostaMetadata() {
        FoodItemImage foodItemImage = new FoodItemImage("dadosBase64", "imagem.jpg", "jpg");
        FoodItemImageDataResponse response = new FoodItemImageDataResponse(foodItemImage);

        Assertions.assertNotNull(response.get_response());
        Assertions.assertNotNull(response.get_data());
        Assertions.assertEquals("imagem.jpg", response.get_data().getFileName());
    }

    @Test
    void deveCriarRespostaVazia() {
        FoodItemImageDataResponse response = new FoodItemImageDataResponse();

        Assertions.assertNull(response.get_response());
        Assertions.assertNull(response.get_data());
    }

    @Test
    void deveAtualizarRespostaMetadata() {
        FoodItemImageDataResponse response = new FoodItemImageDataResponse();
        ResponseMetada responseMetada = new ResponseMetada("404", "Não encontrado");
        response.set_response(responseMetada);

        Assertions.assertNotNull(response.get_response());
        Assertions.assertEquals("404", response.get_response().getCode());
    }

    @Test
    void deveAtualizarImagemNaResposta() {
        FoodItemImageDataResponse response = new FoodItemImageDataResponse();
        FoodItemImage foodItemImage = new FoodItemImage("dadosBase64", "novaImagem.jpg", "jpg");
        response.set_data(foodItemImage);

        Assertions.assertNotNull(response.get_data());
        Assertions.assertEquals("novaImagem.jpg", response.get_data().getFileName());
    }
}