package br.com.tp.lanchescaieiras.fooditem.application;

import br.com.tp.lanchescaieiras.commons.dtos.FoodItemImageDTO;
import br.com.tp.lanchescaieiras.fooditem.adapters.FoodItemGatewayImpl;
import br.com.tp.lanchescaieiras.fooditem.domain.FoodItemImage;
import br.com.tp.lanchescaieiras.fooditem.domain.exceptions.FoodItemException;
import br.com.tp.lanchescaieiras.fooditem.external.config.FoodItemConfig;
import br.com.tp.lanchescaieiras.fooditem.adapters.FoodItemMapper;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CreateFoodItemImageUseCase {
    public FoodItemImageDTO execute(Integer foodItemId, FoodItemImageDTO foodItemImageDTO, Integer maxImages, FoodItemGatewayImpl foodItemGateway, FoodItemMapper foodItemMapper, FoodItemConfig foodItemConfig) {
        List<FoodItemImage> foodItemImageList = foodItemGateway.findAllFoodItemImagesByFoodItemId(foodItemId,false);

        if( foodItemImageList.size() >= maxImages){
            throw new FoodItemException("O item de alimentação com id " + foodItemId +" já possui 5 imagens. Subistitua uma imagem já existente",409);
        }

        FoodItemImage foodItemImage = foodItemMapper.imageDtoToDomain(foodItemImageDTO);
        foodItemImage.validateImage(foodItemConfig);

        if (foodItemImage.getImageError()!= null) {
            throw new FoodItemException(foodItemImage.getImageError(), 404);
        }

        foodItemImage = setImageInfo(foodItemId, foodItemImage, foodItemImageList, foodItemConfig.getMaxImages());
        foodItemGateway.create(foodItemImage);

        return foodItemMapper.imageDomainToDto(foodItemImage);
    }


    private FoodItemImage setImageInfo(Integer foodItemId, FoodItemImage foodItemImage, List<FoodItemImage> foodItemImageList, Integer maxImages){
        //Obtendo Id base para imagens
        int baseImageId= foodItemId * 10;

        //Criando lista de possíveis ids para o Item de Alimentação
        List<Integer> possiblesIds = IntStream.rangeClosed(1,maxImages).map(i -> foodItemId * 10 + i).boxed().collect(Collectors.toList());

        //Lista de ids existentes
        List<Integer> existsIds =foodItemImageList.stream().map(FoodItemImage::getId).collect(Collectors.toList());

        //Removendo id existentes da lista de ids possíveis
        possiblesIds.removeAll(existsIds);

        //Alterando informações da imagem
        foodItemImage.setFoodItemId(foodItemId);
        foodItemImage.setId(possiblesIds.stream().min(Integer::compareTo).orElse(null));
        foodItemImage.setFileName(foodItemImage.getId()+"."+foodItemImage.getFileExtension());
        return foodItemImage;
    }
}
