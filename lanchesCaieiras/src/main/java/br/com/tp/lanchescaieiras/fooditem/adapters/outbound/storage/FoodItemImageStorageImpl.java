package br.com.tp.lanchescaieiras.fooditem.adapters.outbound.storage;

import br.com.tp.lanchescaieiras.fooditem.adapters.outbound.entities.JpaFoodItemImageEntity;
import br.com.tp.lanchescaieiras.fooditem.infraestructure.config.FoodItemImageConfig;
import br.com.tp.lanchescaieiras.fooditem.infraestructure.exceptions.FoodItemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

@Component
public class FoodItemImageStorageImpl implements FoodItemImageStorage {

    private static final Logger log = LoggerFactory.getLogger(FoodItemImageStorageImpl.class);
    public final FoodItemImageConfig imageConfig;

    public FoodItemImageStorageImpl(FoodItemImageConfig imageConfig) {
        this.imageConfig = imageConfig;
    }

    @Override
    public void saveImageFile(JpaFoodItemImageEntity jpaFoodItemImageEntity) throws FoodItemException {
        byte[] fileData = Base64.getDecoder().decode(jpaFoodItemImageEntity.get_data());

        File directory = new File(imageConfig.getDirectory());
        if (!directory.exists()) {
            directory.mkdirs();
        }
        File file = new File(directory, jpaFoodItemImageEntity.getFileName());
        try (FileOutputStream fos = new FileOutputStream(file)) {
            log.info("Salvando arquivo: {}", file.getName());
            fos.write(fileData);
        } catch (IOException e) {
            throw new FoodItemException("Erro ao salvar o arquivo", 500);
        }
    }

    @Override
    public void deleteImageFile(String fileName) throws FoodItemException {
        File file = new File(imageConfig.getDirectory() + fileName);
        if(file.exists()) {
            log.info("Deletando arquivo: {}", file.getName());
            file.delete();
        }
    }

    @Override
    public String getImgaeData(String fileName) throws FoodItemException {
        File file = new File(imageConfig.getDirectory() + fileName);
        if (file.exists()) {
            byte[] fileData = new byte[(int) file.length()];
            try (FileInputStream fis = new FileInputStream(file)) {
                log.info("Buscando arquivo: {}", file.getName());
                fis.read(fileData);
            } catch (IOException e) {
                throw new FoodItemException("Erro ao ler o arquivo", 500);
            }
            return Base64.getEncoder().encodeToString(fileData);
        } else {
            throw new FoodItemException("Arquivo não encontrado", 404);
        }
    }
}
