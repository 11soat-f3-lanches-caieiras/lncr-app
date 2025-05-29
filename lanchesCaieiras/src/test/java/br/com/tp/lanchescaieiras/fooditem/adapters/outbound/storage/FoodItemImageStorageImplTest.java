package br.com.tp.lanchescaieiras.fooditem.adapters.outbound.storage;

import br.com.tp.lanchescaieiras.fooditem.adapters.outbound.entities.JpaFoodItemImageEntity;
import br.com.tp.lanchescaieiras.fooditem.infraestructure.config.FoodItemImageConfig;
import br.com.tp.lanchescaieiras.fooditem.infraestructure.exceptions.FoodItemException;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import java.io.File;
import java.nio.file.Files;
import java.util.Base64;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FoodItemImageStorageImplTest {

    private FoodItemImageConfig config;
    private FoodItemImageStorageImpl storage;
    private File tempDir;

    @BeforeEach
    void setup() throws Exception {
        tempDir = Files.createTempDirectory("imgtest").toFile();
        config = mock(FoodItemImageConfig.class);
        when(config.getDirectory()).thenReturn(tempDir.getAbsolutePath() + File.separator);
        storage = new FoodItemImageStorageImpl(config);
    }

    @AfterEach
    void cleanup() {
        for (File file : tempDir.listFiles()) {
            file.delete();
        }
        tempDir.delete();
    }

    @Test
    void testSaveImageFileAndGetImgaeData() throws Exception {
        String content = "imagem";
        String base64 = Base64.getEncoder().encodeToString(content.getBytes());
        JpaFoodItemImageEntity entity = new JpaFoodItemImageEntity();
        entity.setFileName("test.png");
        entity.set_data(base64);

        storage.saveImageFile(entity);

        File saved = new File(tempDir, "test.png");
        assertTrue(saved.exists());

        String loadedBase64 = storage.getImgaeData("test.png");
        assertEquals(base64, loadedBase64);
    }

    @Test
    void testDeleteImageFile() throws Exception {
        File file = new File(tempDir, "delete.png");
        Files.write(file.toPath(), "abc".getBytes());
        assertTrue(file.exists());

        storage.deleteImageFile("delete.png");
        assertFalse(file.exists());
    }

    @Test
    void testGetImgaeDataFileNotFound() {
        FoodItemException ex = assertThrows(FoodItemException.class, () -> storage.getImgaeData("notfound.png"));
        assertEquals(404, ex.getCode());
    }

    @Test
    void testSaveImageFileThrows() {
        JpaFoodItemImageEntity entity = new JpaFoodItemImageEntity();
        entity.setFileName("/invalid/path/test.png");
        entity.set_data(Base64.getEncoder().encodeToString("fail".getBytes()));
        // Força IOException ao tentar salvar em caminho inválido
        FoodItemException ex = assertThrows(FoodItemException.class, () -> storage.saveImageFile(entity));
        assertEquals(500, ex.getCode());
    }
}
