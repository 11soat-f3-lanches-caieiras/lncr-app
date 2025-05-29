package br.com.tp.lanchescaieiras.fooditem.domain;

import br.com.tp.lanchescaieiras.fooditem.infraestructure.exceptions.FoodItemException;
import org.junit.jupiter.api.Test;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class FoodItemImageTest {

    @Test
    void testConstructorAndGetters() {
        FoodItemImage img = new FoodItemImage(1, "data", "loc", "file.jpg", "jpg");
        assertEquals(1, img.getId());
        assertEquals("data", img.get_data());
        assertEquals("loc", img.getLocation());
        assertEquals("file.jpg", img.getFileName());
        assertEquals("jpg", img.getFileExtension());
    }

    @Test
    void testSetters() {
        FoodItemImage img = new FoodItemImage();
        img.setId(2);
        img.set_data("abc");
        img.setLocation("local");
        img.setFileName("img.png");
        img.setFileExtension("png");

        assertEquals(2, img.getId());
        assertEquals("abc", img.get_data());
        assertEquals("local", img.getLocation());
        assertEquals("img.png", img.getFileName());
        assertEquals("png", img.getFileExtension());
    }

    @Test
    void testValidateImageSizeTrue() {
        FoodItemImage img = new FoodItemImage();
        String base64 = Base64.getEncoder().encodeToString("12345".getBytes());
        assertTrue(img.validateImageSize(base64, 10));
    }

    @Test
    void testValidateImageSizeFalse() {
        FoodItemImage img = new FoodItemImage();
        String base64 = Base64.getEncoder().encodeToString("1234567890".getBytes());
        assertFalse(img.validateImageSize(base64, 5));
    }

    @Test
    void testGetDecodeImageDataValid() {
        FoodItemImage img = new FoodItemImage();
        String base64 = Base64.getEncoder().encodeToString("abc".getBytes());
        byte[] decoded = img.getDecodeImageData(base64);
        assertArrayEquals("abc".getBytes(), decoded);
    }

    @Test
    void testGetDecodeImageDataInvalidThrows() {
        FoodItemImage img = new FoodItemImage();
        assertThrows(FoodItemException.class, () -> img.getDecodeImageData("not_base64"));
    }

    @Test
    void testValidateImageExtension() {
        FoodItemImage img = new FoodItemImage();
        // Simula um header hexadecimal para PNG (89504E47)
        byte[] pngBytes = new byte[]{(byte)0x89, 0x50, 0x4E, 0x47};
        String base64 = Base64.getEncoder().encodeToString(pngBytes);
        assertTrue(img.validateImageExtention(base64, "89504E47"));
        assertFalse(img.validateImageExtention(base64, "FFD8FF"));
    }

    @Test
    void testValidateImage() {
        FoodItemImage img = new FoodItemImage();
        byte[] pngBytes = new byte[]{(byte)0x89, 0x50, 0x4E, 0x47};
        String base64 = Base64.getEncoder().encodeToString(pngBytes);
        Map<String, String> config = new HashMap<>();
        config.put("png", "89504E47");
        String result = img.validateImage(base64, config, 10);
        assertEquals("png", result);
    }

    @Test
    void testValidateImageInvalidExtension() {
        FoodItemImage img = new FoodItemImage();
        byte[] gifBytes = new byte[]{0x47, 0x49, 0x46, 0x38};
        String base64 = Base64.getEncoder().encodeToString(gifBytes);
        Map<String, String> config = new HashMap<>();
        config.put("png", "89504E47");
        String result = img.validateImage(base64, config, 10);
        assertTrue(result.contains("Imagens inválidas"));
    }

    @Test
    void testValidateImageInvalidSize() {
        FoodItemImage img = new FoodItemImage();
        byte[] pngBytes = new byte[]{(byte)0x89, 0x50, 0x4E, 0x47};
        String base64 = Base64.getEncoder().encodeToString(pngBytes);
        Map<String, String> config = new HashMap<>();
        config.put("png", "89504E47");
        String result = img.validateImage(base64, config, 2);
        assertTrue(result.contains("Tamanho da imagem excede o limite"));
    }
}
