package br.com.tp.lanchescaieiras.fooditem.adapters.outbound.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class JpaFoodItemImageEntityTest {

    @Test
    void deveCriarEntidadeComTodosOsCampos() {
        JpaFoodItemImageEntity entidade = new JpaFoodItemImageEntity(1, 10, "dadosBase64", "caminho/local", "imagem", "jpg", null);

        Assertions.assertEquals(1, entidade.getId());
        Assertions.assertEquals(10, entidade.getFoodItemId());
        Assertions.assertEquals("dadosBase64", entidade.get_data());
        Assertions.assertEquals("caminho/local", entidade.location);
        Assertions.assertEquals("imagem", entidade.getFileName());
        Assertions.assertEquals("jpg", entidade.getFileExtension());
    }

    @Test
    void devePermitirAtualizarCampos() {
        JpaFoodItemImageEntity entidade = new JpaFoodItemImageEntity();
        entidade.setId(2);
        entidade.setFoodItemId(20);
        entidade.set_data("novosDados");
        entidade.setLocation("novo/local");
        entidade.setFileName("novaImagem");
        entidade.setFileExtension("png");

        Assertions.assertEquals(2, entidade.getId());
        Assertions.assertEquals(20, entidade.getFoodItemId());
        Assertions.assertEquals("novosDados", entidade.get_data());
        Assertions.assertEquals("novo/local", entidade.location);
        Assertions.assertEquals("novaImagem", entidade.getFileName());
        Assertions.assertEquals("png", entidade.getFileExtension());
    }

    @Test
    void deveGerarIdDeImagemCorretamente() {
        JpaFoodItemImageEntity entidade = new JpaFoodItemImageEntity();
        Integer idGerado = entidade.setImageId(5, 100);

        Assertions.assertEquals(1005, idGerado);
    }

    @Test
    void deveGerarNomeDeArquivoCorretamente() {
        JpaFoodItemImageEntity entidade = new JpaFoodItemImageEntity();
        String nomeArquivo = entidade.setFileName(123, "jpg");

        Assertions.assertEquals("123.jpg", nomeArquivo);
    }

    @Test
    void deveLidarComValoresNulos() {
        JpaFoodItemImageEntity entidade = new JpaFoodItemImageEntity(null, null, null, null, null, null, null);

        Assertions.assertNull(entidade.getId());
        Assertions.assertNull(entidade.getFoodItemId());
        Assertions.assertNull(entidade.get_data());
        Assertions.assertNull(entidade.location);
        Assertions.assertNull(entidade.getFileName());
        Assertions.assertNull(entidade.getFileExtension());
    }
}