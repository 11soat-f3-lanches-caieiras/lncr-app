package br.com.tp.lanchescaieiras.fooditem.application.services;

import br.com.tp.lanchescaieiras.fooditem.domain.FoodItem;
import br.com.tp.lanchescaieiras.fooditem.domain.FoodItemCategory;
import br.com.tp.lanchescaieiras.fooditem.domain.FoodItemRepository;
import br.com.tp.lanchescaieiras.fooditem.infraestructure.exceptions.FoodItemException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

class FoodItemServicesImplTest {

    private final FoodItemRepository repository = Mockito.mock(FoodItemRepository.class);
    private final FoodItemServicesImpl service = new FoodItemServicesImpl(repository);

    @Test
    void deveAdicionarSandwichComSucesso() {
        FoodItem sandwich = new FoodItem(1, "Sanduíche Natural", "Sanduíche com peito de peru", 15.0, FoodItemCategory.SANDWICH, null);
        Mockito.when(repository.save(sandwich)).thenReturn(sandwich);

        FoodItem result = service.addFoodItem(sandwich);

        Assertions.assertNotNull(result);
        Assertions.assertEquals("Sanduíche Natural", result.getName());
    }

    @Test
    void deveRetornarBebidasDisponiveis() {
        FoodItem beverage = new FoodItem(2, "Suco de Laranja", "Suco natural de laranja", 8.0, FoodItemCategory.BEVERAGE, null);
        Mockito.when(repository.findAllByCategory(FoodItemCategory.BEVERAGE)).thenReturn(List.of(beverage));

        List<FoodItem> result = service.getFoodItemsByCategory(FoodItemCategory.BEVERAGE);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("Suco de Laranja", result.get(0).getName());
    }

    @Test
    void deveRetornarSobremesaPorId() {
        FoodItem dessert = new FoodItem(3, "Pudim", "Pudim de leite condensado", 10.0, FoodItemCategory.DESSERT, null);
        Mockito.when(repository.findById(3)).thenReturn(Optional.of(dessert));

        FoodItem result = service.getFoodItemById(3);

        Assertions.assertNotNull(result);
        Assertions.assertEquals("Pudim", result.getName());
    }

    @Test
    void deveAtualizarPrecoDeSandwich() {
        FoodItem existingSandwich = new FoodItem(4, "Sanduíche de Frango", "Sanduíche com frango grelhado", 12.0, FoodItemCategory.SANDWICH, null);
        FoodItem updatedSandwich = new FoodItem(4, "Sanduíche de Frango", "Sanduíche com frango grelhado", 14.0, FoodItemCategory.SANDWICH, null);

        Mockito.when(repository.findById(4)).thenReturn(Optional.of(existingSandwich));
        Mockito.when(repository.save(updatedSandwich)).thenReturn(updatedSandwich);

        FoodItem result = service.updateFoodItemPrice(4, 14.0);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(14.0, result.getPrice());
    }

    @Test
    void deveLancarExcecaoAoDeletarSobremesaInexistente() {
        Mockito.when(repository.findById(99)).thenReturn(Optional.empty());

        FoodItemException exception = Assertions.assertThrows(FoodItemException.class, () -> service.deleteFoodItem(99));

        Assertions.assertEquals("Item de alimentação não encontrado com o ID: 99", exception.getMessage());
    }
}