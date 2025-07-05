package br.com.tp.lanchescaieiras._core.applications.fooditem;

import br.com.tp.lanchescaieiras._core.commons.dtos.fooditem.FoodItemImageDTO;
import br.com.tp.lanchescaieiras._core.commons.interfaces.fooditem.FoodItemGateway;
import br.com.tp.lanchescaieiras._core.domain.exceptions.FoodItemException;
import br.com.tp.lanchescaieiras._core.domain.fooditem.FoodItemImage;
import br.com.tp.lanchescaieiras._core.domain.fooditem.FoodItemImageRules;

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
        Integer maxImages = foodItemImageRules.getMaxNumberOfImages();
        List<FoodItemImage> foodItemImageList = foodItemGateway.getAllImagesByFoodItemId(foodItemId, false);

        if (foodItemImageList.size() >= maxImages) {
            throw new FoodItemException("O item de alimentação com id " + foodItemId + " já possui 5 imagens. Subistitua uma imagem já existente", 409);
        }

        FoodItemImage foodItemImage = new FoodItemImage(foodItemImageDTO, foodItemImageRules);

        if (foodItemImage.getImageError() != null) {
            throw new FoodItemException(foodItemImage.getImageError(), 404);
        }

        foodItemImage = setImageInfo(foodItemId, foodItemImage, foodItemImageList, maxImages);
        foodItemGateway.save(foodItemImage);

        return foodItemImage;
    }


    private FoodItemImage setImageInfo(Integer foodItemId, FoodItemImage foodItemImage, List<FoodItemImage> foodItemImageList, Integer maxImages) {
        //Obtendo Id base para imagens
        int baseImageId = foodItemId * 10;

        //Criando lista de possíveis ids para o Item de Alimentação
        List<Integer> possiblesIds = IntStream.rangeClosed(1, maxImages).map(i -> foodItemId * 10 + i).boxed().collect(Collectors.toList());

        //Lista de ids existentes
        List<Integer> existsIds = foodItemImageList.stream().map(FoodItemImage::getId).collect(Collectors.toList());

        //Removendo id existentes da lista de ids possíveis
        possiblesIds.removeAll(existsIds);

        //Alterando informações da imagem
        foodItemImage.setFoodItemId(foodItemId);
        foodItemImage.setId(possiblesIds.stream().min(Integer::compareTo).orElse(null));
        foodItemImage.setFileName(foodItemImage.getId() + "." + foodItemImage.getFileExtension());
        return foodItemImage;
    }
}
