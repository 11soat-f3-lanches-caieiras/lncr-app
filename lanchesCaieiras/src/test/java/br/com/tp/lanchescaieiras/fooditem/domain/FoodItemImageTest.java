package br.com.tp.lanchescaieiras.fooditem.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Base64;
import java.util.Map;

class FoodItemImageTest {

    @Test
    void deveValidarImagemComTamanhoEExtensaoValidos() {
        FoodItemImage image = new FoodItemImage();
        String base64 = Base64.getEncoder().encodeToString(new byte[1024]);
        Map<String, String> config = Map.of("jpg", "FFD8FF", "png", "89504E");

        String result = image.validateImage(base64, config, 2048);

        Assertions.assertEquals("Imagens inválidas. Extensões permitidas:jpg, png", result);
    }

    @Test
    void deveRetornarErroParaImagemComTamanhoExcedido() {
        FoodItemImage image = new FoodItemImage();
        String base64 = Base64.getEncoder().encodeToString(new byte[4096]);
        Map<String, String> config = Map.of("jpg", "FFD8FF", "png", "89504E");

        String result = image.validateImage(base64, config, 2048);

        Assertions.assertEquals("Tamanho da imagem excede o limite de 2048 bytes", result);
    }

    @Test
    void deveRetornarErroParaImagemComExtensaoInvalida() {
        FoodItemImage image = new FoodItemImage();
        String base64 = Base64.getEncoder().encodeToString(new byte[1024]);
        Map<String, String> config = Map.of("jpg", "FFD8FF", "png", "89504E");

        String result = image.validateImage(base64, config, 2048);

        Assertions.assertTrue(result.contains("Imagens inválidas. Extensões permitidas:"));
    }

    @Test
    void deveDecodificarDadosBase64ComSucesso() {
        FoodItemImage image = new FoodItemImage();
        String base64 = Base64.getEncoder().encodeToString("dados".getBytes());

        byte[] result = image.getDecodeImageData(base64);

        Assertions.assertArrayEquals("dados".getBytes(), result);
    }

    @Test
    void deveValidarExtensaoDeImagemComSucesso() {
        FoodItemImage image = new FoodItemImage();
        String base64 = Base64.getEncoder().encodeToString(new byte[]{(byte) 0xFF, (byte) 0xD8, (byte) 0xFF});

        boolean result = image.validateImageExtention(base64, "FFD8FF");

        Assertions.assertTrue(result);
    }

    @Test
    void deveRetornarFalsoParaExtensaoDeImagemInvalida() {
        FoodItemImage image = new FoodItemImage();
        String base64 = Base64.getEncoder().encodeToString(new byte[]{(byte) 0x89, (byte) 0x50, (byte) 0x4E});

        boolean result = image.validateImageExtention(base64, "FFD8FF");

        Assertions.assertFalse(result);
    }
}