package br.com.tp.lanchescaieiras.fooditem.domain;

import br.com.tp.lanchescaieiras.commons.domain.ResponseMetada;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FoodItemImageResponseTest {

    @Test
    void deveCriarRespostaComImagemERespostaMetadata() {
        ResponseMetada responseMetada = new ResponseMetada("200", "Sucesso");
        FoodItemImage foodItemImage = new FoodItemImage("dadosBase64", "imagem.jpg", "jpg");
        FoodItemImageResponse response = new FoodItemImageResponse(responseMetada, foodItemImage);

        Assertions.assertNotNull(response.get_response());
        Assertions.assertNotNull(response.getImage());
        Assertions.assertEquals("200", response.get_response().getCode());
        Assertions.assertEquals("imagem.jpg", response.getImage().getFileName());
    }

    @Test
    void deveCriarRespostaComImagemSemRespostaMetadata() {
        FoodItemImage foodItemImage = new FoodItemImage("dadosBase64", "imagem.jpg", "jpg");
        FoodItemImageResponse response = new FoodItemImageResponse(foodItemImage);

        Assertions.assertNotNull(response.get_response());
        Assertions.assertNotNull(response.getImage());
        Assertions.assertEquals("imagem.jpg", response.getImage().getFileName());
    }

    @Test
    void deveCriarRespostaVazia() {
        FoodItemImageResponse response = new FoodItemImageResponse();

        Assertions.assertNull(response.get_response());
        Assertions.assertNull(response.getImage());
    }

    @Test
    void deveAtualizarRespostaMetadata() {
        FoodItemImageResponse response = new FoodItemImageResponse();
        ResponseMetada responseMetada = new ResponseMetada("404", "Não encontrado");
        response.set_response(responseMetada);

        Assertions.assertNotNull(response.get_response());
        Assertions.assertEquals("404", response.get_response().getCode());
    }

    @Test
    void deveAtualizarImagemNaResposta() {
        FoodItemImageResponse response = new FoodItemImageResponse();
        FoodItemImage foodItemImage = new FoodItemImage("dadosBase64", "novaImagem.jpg", "jpg");
        response.setImage(foodItemImage);

        Assertions.assertNotNull(response.getImage());
        Assertions.assertEquals("novaImagem.jpg", response.getImage().getFileName());
    }
}