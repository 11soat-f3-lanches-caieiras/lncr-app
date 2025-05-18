package br.com.tp.lanchescaieiras.fooditem.adapters.outbound.storage;

import br.com.tp.lanchescaieiras.fooditem.adapters.outbound.entities.JpaFoodItemImageEntity;
import br.com.tp.lanchescaieiras.fooditem.infraestructure.config.FoodItemImageConfig;
import br.com.tp.lanchescaieiras.fooditem.infraestructure.exceptions.FoodItemException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.File;
import java.io.IOException;
import java.util.Base64;

class FoodItemImageStorageImplTest {

    private final FoodItemImageConfig config = Mockito.mock(FoodItemImageConfig.class);
    private final FoodItemImageStorageImpl storage = new FoodItemImageStorageImpl(config);

    @Test
    void deveSalvarArquivoDeImagemComSucesso() throws FoodItemException {
        JpaFoodItemImageEntity entity = new JpaFoodItemImageEntity(1, 1, Base64.getEncoder().encodeToString("dados".getBytes()), null, "imagem.jpg", "jpg", null);
        Mockito.when(config.getDirectory()).thenReturn("test-directory/");

        storage.saveImageFile(entity);

        File file = new File("test-directory/imagem.jpg");
        Assertions.assertTrue(file.exists());
        file.delete();
    }

    @Test
    void deveLancarExcecaoAoSalvarArquivoComErro() {
        JpaFoodItemImageEntity entity = new JpaFoodItemImageEntity(1, 1, "dadosInvalidos", null, "imagem.jpg", "jpg", null);
        Mockito.when(config.getDirectory()).thenReturn("test-directory/");

        FoodItemException exception = Assertions.assertThrows(FoodItemException.class, () -> storage.saveImageFile(entity));

        Assertions.assertEquals("Erro ao salvar o arquivo", exception.getMessage());
    }

    @Test
    void deveDeletarArquivoDeImagemComSucesso() throws FoodItemException, IOException {
        File file = new File("test-directory/imagem.jpg");
        file.getParentFile().mkdirs();
        file.createNewFile();
        Mockito.when(config.getDirectory()).thenReturn("test-directory/");

        storage.deleteImageFile("imagem.jpg");

        Assertions.assertFalse(file.exists());
    }

    @Test
    void deveRetornarDadosDeImagemBase64ComSucesso() throws FoodItemException, IOException {
        File file = new File("test-directory/imagem.jpg");
        file.getParentFile().mkdirs();
        try (var fos = new java.io.FileOutputStream(file)) {
            fos.write("dados".getBytes());
        }
        Mockito.when(config.getDirectory()).thenReturn("test-directory/");

        String base64Data = storage.getImgaeData("imagem.jpg");

        Assertions.assertEquals(Base64.getEncoder().encodeToString("dados".getBytes()), base64Data);
        file.delete();
    }

    @Test
    void deveLancarExcecaoAoBuscarArquivoInexistente() {
        Mockito.when(config.getDirectory()).thenReturn("test-directory/");

        FoodItemException exception = Assertions.assertThrows(FoodItemException.class, () -> storage.getImgaeData("arquivo_inexistente.jpg"));

        Assertions.assertEquals("Arquivo não encontrado", exception.getMessage());
    }
}