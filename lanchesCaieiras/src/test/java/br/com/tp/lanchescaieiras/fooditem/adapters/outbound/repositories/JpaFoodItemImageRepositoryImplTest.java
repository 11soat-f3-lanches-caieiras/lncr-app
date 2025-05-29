package br.com.tp.lanchescaieiras.fooditem.adapters.outbound.repositories;

import br.com.tp.lanchescaieiras.fooditem.adapters.outbound.entities.JpaFoodItemImageEntity;
import br.com.tp.lanchescaieiras.fooditem.adapters.outbound.storage.FoodItemImageStorage;
import br.com.tp.lanchescaieiras.fooditem.application.mappers.FoodItemImageMapper;
import br.com.tp.lanchescaieiras.fooditem.domain.FoodItem;
import br.com.tp.lanchescaieiras.fooditem.domain.FoodItemImage;
import br.com.tp.lanchescaieiras.fooditem.infraestructure.config.FoodItemImageConfig;
import br.com.tp.lanchescaieiras.fooditem.infraestructure.exceptions.FoodItemException;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class JpaFoodItemImageRepositoryImplTest {

    @Test
    void testSaveImages() {
        JpaFoodItemImageRepository repo = mock(JpaFoodItemImageRepository.class);
        FoodItemImageStorage storage = mock(FoodItemImageStorage.class);
        FoodItemImageMapper mapper = mock(FoodItemImageMapper.class);
        FoodItemImageConfig config = mock(FoodItemImageConfig.class);

        FoodItemImage img = new FoodItemImage();
        img.set_data("iVBORw0KGgoAAAANSUhEUgAAAGcAAACzCAYAAACU7gLAAAAAAXNSR0");
        img.setFileExtension("png");
        FoodItem foodItem = new FoodItem();
        foodItem.setId(1);
        // Corrigido: use ArrayList para permitir modificações
        foodItem.setImages(new java.util.ArrayList<>(List.of(img)));

        JpaFoodItemImageRepositoryImpl impl = new JpaFoodItemImageRepositoryImpl(repo, mapper, storage, config);

        // Mock internals
        when(mapper.domainToJpa(any(), anyInt(), anyInt())).thenReturn(new JpaFoodItemImageEntity());
        when(mapper.jpaToDomain(any())).thenReturn(img);
        when(config.getLocationPrefix()).thenReturn("/images");
        doNothing().when(storage).saveImageFile(any());
        when(repo.save(any())).thenReturn(new JpaFoodItemImageEntity());

        FoodItem result = impl.saveImages(foodItem);

        assertEquals(1, result.getImages().size());
        assertEquals(img, result.getImages().get(0));
    }

    @Test
    void testFindAllImagesByFoodItemId() {
        JpaFoodItemImageRepository repo = mock(JpaFoodItemImageRepository.class);
        FoodItemImageStorage storage = mock(FoodItemImageStorage.class);
        FoodItemImageMapper mapper = mock(FoodItemImageMapper.class);
        FoodItemImageConfig config = mock(FoodItemImageConfig.class);

        JpaFoodItemImageEntity entity = new JpaFoodItemImageEntity();
        entity.setId(1);
        entity.setFileName("file.png");
        when(repo.findAllByFoodItemId(1)).thenReturn(List.of(entity));
        when(config.getLocationPrefix()).thenReturn("/images");
        when(mapper.jpaToDomain(any())).thenReturn(new FoodItemImage());

        FoodItem foodItem = new FoodItem();
        foodItem.setId(1);

        JpaFoodItemImageRepositoryImpl impl = new JpaFoodItemImageRepositoryImpl(repo, mapper, storage, config);
        FoodItem result = impl.findAllImagesByFoodItemId(foodItem);

        assertEquals(1, result.getImages().size());
    }

    @Test
    void testDeleteFoodItemImagesByFoodItemId() {
        JpaFoodItemImageRepository repo = mock(JpaFoodItemImageRepository.class);
        FoodItemImageStorage storage = mock(FoodItemImageStorage.class);
        FoodItemImageMapper mapper = mock(FoodItemImageMapper.class);
        FoodItemImageConfig config = mock(FoodItemImageConfig.class);

        JpaFoodItemImageEntity entity = new JpaFoodItemImageEntity();
        entity.setId(1);
        entity.setFileName("file.png");
        when(repo.findAllByFoodItemId(1)).thenReturn(List.of(entity));
        doNothing().when(storage).deleteImageFile(any());
        doNothing().when(repo).deleteById(anyInt());

        JpaFoodItemImageRepositoryImpl impl = new JpaFoodItemImageRepositoryImpl(repo, mapper, storage, config);

        assertDoesNotThrow(() -> impl.deleteFoodItemImagesByFoodItemId(1));
    }

    @Test
    void testFindImageByIdFound() {
        JpaFoodItemImageRepository repo = mock(JpaFoodItemImageRepository.class);
        FoodItemImageStorage storage = mock(FoodItemImageStorage.class);
        FoodItemImageMapper mapper = mock(FoodItemImageMapper.class);
        FoodItemImageConfig config = mock(FoodItemImageConfig.class);

        JpaFoodItemImageEntity entity = new JpaFoodItemImageEntity();
        entity.setId(1);
        entity.setFileName("file.png");
        when(repo.findById(1)).thenReturn(Optional.of(entity));
        when(storage.getImgaeData("file.png")).thenReturn("data");
        when(mapper.jpaToImageData(entity)).thenReturn(new FoodItemImage());

        JpaFoodItemImageRepositoryImpl impl = new JpaFoodItemImageRepositoryImpl(repo, mapper, storage, config);

        FoodItemImage result = impl.findImageById(1);

        assertNotNull(result);
    }

    @Test
    void testFindImageByIdNotFound() {
        JpaFoodItemImageRepository repo = mock(JpaFoodItemImageRepository.class);
        FoodItemImageStorage storage = mock(FoodItemImageStorage.class);
        FoodItemImageMapper mapper = mock(FoodItemImageMapper.class);
        FoodItemImageConfig config = mock(FoodItemImageConfig.class);

        when(repo.findById(2)).thenReturn(Optional.empty());

        JpaFoodItemImageRepositoryImpl impl = new JpaFoodItemImageRepositoryImpl(repo, mapper, storage, config);

        assertThrows(FoodItemException.class, () -> impl.findImageById(2));
    }

    @Test
    void testUpdateImageByIdWithData() {
        JpaFoodItemImageRepository repo = mock(JpaFoodItemImageRepository.class);
        FoodItemImageStorage storage = mock(FoodItemImageStorage.class);
        FoodItemImageMapper mapper = mock(FoodItemImageMapper.class);
        FoodItemImageConfig config = mock(FoodItemImageConfig.class);

        JpaFoodItemImageEntity entity = new JpaFoodItemImageEntity();
        entity.setId(1);
        entity.setFileName("file.png");
        when(repo.findById(1)).thenReturn(Optional.of(entity));
        when(config.getLocationPrefix()).thenReturn("/images");
        when(mapper.jpaToDomain(any())).thenReturn(new FoodItemImage());
        when(repo.save(any())).thenReturn(entity);
        doNothing().when(storage).saveImageFile(any());

        JpaFoodItemImageRepositoryImpl impl = new JpaFoodItemImageRepositoryImpl(repo, mapper, storage, config);

        FoodItemImage img = new FoodItemImage();
        img.set_data("data");
        img.setFileExtension("png");

        FoodItemImage result = impl.updateImageById(1, img);

        assertNotNull(result);
    }

    @Test
    void testUpdateImageByIdNotFound() {
        JpaFoodItemImageRepository repo = mock(JpaFoodItemImageRepository.class);
        FoodItemImageStorage storage = mock(FoodItemImageStorage.class);
        FoodItemImageMapper mapper = mock(FoodItemImageMapper.class);
        FoodItemImageConfig config = mock(FoodItemImageConfig.class);

        when(repo.findById(2)).thenReturn(Optional.empty());

        JpaFoodItemImageRepositoryImpl impl = new JpaFoodItemImageRepositoryImpl(repo, mapper, storage, config);

        FoodItemImage img = new FoodItemImage();
        img.set_data("data");
        img.setFileExtension("png");

        assertThrows(FoodItemException.class, () -> impl.updateImageById(2, img));
    }
}
