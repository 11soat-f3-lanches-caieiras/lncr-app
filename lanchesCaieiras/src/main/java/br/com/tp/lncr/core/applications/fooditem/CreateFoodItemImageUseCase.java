package br.com.tp.lncr.core.applications.fooditem;

import br.com.tp.lncr.core.commons.dtos.fooditem.FoodItemImageDTO;
import br.com.tp.lncr.core.commons.exceptions.FoodItemException;
import br.com.tp.lncr.core.commons.interfaces.fooditem.FoodItemGateway;
import br.com.tp.lncr.core.commons.utils.FoodItemImageRules;
import br.com.tp.lncr.core.commons.utils.Logger;
import br.com.tp.lncr.core.domain.fooditem.FoodItemImage;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CreateFoodItemImageUseCase {

    private final FoodItemGateway foodItemGateway;
    private final FoodItemImageRules foodItemImageRules;

    public CreateFoodItemImageUseCase(FoodItemGateway foodItemGateway, FoodItemImageRules foodItemImageRules) {
        this.foodItemGateway = foodItemGateway;
        this.foodItemImageRules = foodItemImageRules;
    }

    public FoodItemImage execute(Integer foodItemId, FoodItemImageDTO foodItemImageDTO) {
        Logger.info("Iniciando criação de imagem para o item de alimentação com id: " + foodItemId);
        Integer maxImages = foodItemImageRules.getMaxNumberOfImages();
        List<FoodItemImage> foodItemImageList = foodItemGateway.getAllImagesByFoodItemId(foodItemId, false);

        if (foodItemImageList.size() >= maxImages) {
            throw new FoodItemException("O item de alimentação com id " + foodItemId + " já possui 5 imagens. Subistitua uma imagem já existente", 409);
        }

        FoodItemImage foodItemImage = new FoodItemImage(foodItemImageDTO, foodItemImageRules);

        if (foodItemImage.getImageError() != null) {
            throw new FoodItemException(foodItemImage.getImageError(), 404);
        }

        foodItemImage = setNewImageInfo(foodItemId, foodItemImage, foodItemImageList, maxImages);
        foodItemGateway.createFoodItemImage(foodItemImage);
        Logger.info("Imagem criada com sucesso para o item de alimentação com id: " + foodItemId);
        return foodItemImage;
    }


    private FoodItemImage setNewImageInfo(Integer foodItemId, FoodItemImage foodItemImage, List<FoodItemImage> foodItemImageList, Integer maxImages) {
        Logger.debug("Definindo informações da imagem para o item de alimentação com id: " + foodItemId);

        int baseImageId = foodItemId * 10; //Obtendo Id base para imagens
        List<Integer> possiblesIds = IntStream.rangeClosed(1, maxImages).map(i -> foodItemId * 10 + i).boxed().collect(Collectors.toList()); //Criando lista de possíveis ids para o Item de Alimentação
        List<Integer> existsIds = foodItemImageList.stream().map(FoodItemImage::getId).collect(Collectors.toList());         //Lista de ids existentes
        possiblesIds.removeAll(existsIds);//Removendo id existentes da lista de ids possíveis

        //Alterando informações da imagem
        foodItemImage.setFoodItemId(foodItemId);
        foodItemImage.setId(possiblesIds.stream().min(Integer::compareTo).orElse(null));
        foodItemImage.setFileName(foodItemImage.getId() + "." + foodItemImage.getFileExtension());
        return foodItemImage;
    }
}
