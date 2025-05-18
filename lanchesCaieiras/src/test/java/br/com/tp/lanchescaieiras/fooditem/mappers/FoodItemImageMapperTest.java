package br.com.tp.lanchescaieiras.fooditem.mappers;

import br.com.tp.lanchescaieiras.fooditem.application.mappers.FoodItemImageMapper;
import br.com.tp.lanchescaieiras.fooditem.domain.FoodItemImage;
import br.com.tp.lanchescaieiras.fooditem.adapters.outbound.entities.JpaFoodItemImageEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

class FoodItemImageMapperTest {

    private final FoodItemImageMapper mapper = Mappers.getMapper(FoodItemImageMapper.class);

    @Test
    void deveMapearDominioParaJpaComSucesso() {
        FoodItemImage foodItemImage = new FoodItemImage("dadosBase64", "imagem.jpg", "jpg");
        JpaFoodItemImageEntity result = mapper.domainToJpa(foodItemImage, 1, 0);

        Assertions.assertNotNull(result);
        Assertions.assertEquals("jpg", result.getFileExtension());
        Assertions.assertEquals("imagem.jpg", result.getFileName());
        Assertions.assertEquals(1, result.getFoodItemId());
    }

    @Test
    void deveMapearJpaParaDominioComSucesso() {
        JpaFoodItemImageEntity jpaEntity = new JpaFoodItemImageEntity();
        jpaEntity.setId(1);
        jpaEntity.setLocation("caminho/para/imagem");
        FoodItemImage result = mapper.jpaToDomain(jpaEntity);

        Assertions.assertNotNull(result);
        Assertions.assertEquals("caminho/para/imagem", result.getLocation());
    }

    @Test
    void deveMapearJpaParaDadosDeImagemComSucesso() {
        JpaFoodItemImageEntity jpaEntity = new JpaFoodItemImageEntity();
        jpaEntity.set_data("dadosBase64");
        FoodItemImage result = mapper.jpaToImageData(jpaEntity);

        Assertions.assertNotNull(result);
        Assertions.assertEquals("dadosBase64", result.get_data());
    }
}