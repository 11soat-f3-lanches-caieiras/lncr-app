package br.com.tp.lanchescaieiras.fooditem.adapters.outbound.repositories;

import br.com.tp.lanchescaieiras.fooditem.adapters.outbound.entities.JpaFoodItemImageEntity;
import br.com.tp.lanchescaieiras.fooditem.adapters.outbound.storage.FoodItemImageStorage;
import br.com.tp.lanchescaieiras.fooditem.domain.FoodItem;
import br.com.tp.lanchescaieiras.fooditem.domain.FoodItemImage;
import br.com.tp.lanchescaieiras.fooditem.domain.FoodItemImageRepository;
import br.com.tp.lanchescaieiras.fooditem.infraestructure.exceptions.FoodItemException;
import br.com.tp.lanchescaieiras.fooditem.mappers.FoodItemImageMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

class JpaFoodItemImageRepositoryImplTest {

    private final JpaFoodItemImageRepository jpaRepository = Mockito.mock(JpaFoodItemImageRepository.class);
    private final FoodItemImageStorage storage = Mockito.mock(FoodItemImageStorage.class);
    private final FoodItemImageMapper mapper = Mockito.mock(FoodItemImageMapper.class);
    private final JpaFoodItemImageRepositoryImpl repository = new JpaFoodItemImageRepositoryImpl(jpaRepository, mapper, storage);

    @Test
    void deveSalvarImagensDeUmFoodItem() {
        FoodItem foodItem = new FoodItem();
        foodItem.setId(1);
        foodItem.setImages(Collections.singletonList(new FoodItemImage("dadosBase64", "imagem.jpg", "jpg")));

        Mockito.when(mapper.domainToJpa(Mockito.any(), Mockito.eq(1), Mockito.anyInt()))
                .thenReturn(new JpaFoodItemImageEntity(1, 1, "dadosBase64", "caminho/local", "imagem", "jpg", null));

        FoodItem result = repository.saveImages(foodItem);

        Assertions.assertNotNull(result.getImages());
        Assertions.assertEquals(1, result.getImages().size());
    }

    @Test
    void deveRetornarTodasAsImagensDeUmFoodItem() {
        FoodItem foodItem = new FoodItem();
        foodItem.setId(1);

        JpaFoodItemImageEntity entity = new JpaFoodItemImageEntity(1, 1, null, "caminho/local", "imagem", "jpg", null);
        Mockito.when(jpaRepository.findAllByFoodItemId(1)).thenReturn(List.of(entity));
        Mockito.when(mapper.jpaToDomain(entity)).thenReturn(new FoodItemImage(null, "imagem.jpg", "jpg"));

        FoodItem result = repository.findAllImagesByFoodItemId(foodItem);

        Assertions.assertNotNull(result.getImages());
        Assertions.assertEquals(1, result.getImages().size());
    }

    @Test
    void deveDeletarImagensDeUmFoodItem() {
        JpaFoodItemImageEntity entity = new JpaFoodItemImageEntity(1, 1, null, "caminho/local", "imagem", "jpg", null);
        Mockito.when(jpaRepository.findAllByFoodItemId(1)).thenReturn(List.of(entity));

        repository.deleteFoodItemImagesByFoodItemId(1);

        Mockito.verify(storage).deleteImageFile("imagem");
        Mockito.verify(jpaRepository).deleteById(1);
    }

    @Test
    void deveLancarExcecaoQuandoImagemNaoForEncontradaPorId() {
        Mockito.when(jpaRepository.findById(1)).thenReturn(Optional.empty());

        FoodItemException exception = Assertions.assertThrows(FoodItemException.class, () -> repository.findImageById(1));

        Assertions.assertEquals("Imagem não encontrada com o ID: 1", exception.getMessage());
    }

    @Test
    void deveAtualizarImagemPorId() {
        JpaFoodItemImageEntity entity = new JpaFoodItemImageEntity(1, 1, null, "caminho/local", "imagem", "jpg", null);
        FoodItemImage foodItemImage = new FoodItemImage("novosDados", "novaImagem.jpg", "jpg");

        Mockito.when(jpaRepository.findById(1)).thenReturn(Optional.of(entity));
        Mockito.when(mapper.jpaToDomain(Mockito.any())).thenReturn(foodItemImage);

        FoodItemImage result = repository.updateImageById(1, foodItemImage);

        Assertions.assertNotNull(result);
        Assertions.assertEquals("novaImagem.jpg", result.getFileName());
    }
}