package br.com.tp.lncr.app.datasources.storage;

import br.com.tp.lncr.app.configs.FoodItemConfig;
import br.com.tp.lncr.core.commons.dtos.fooditem.FoodItemImageDTO;
import br.com.tp.lncr.core.commons.exceptions.FoodItemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Component
public class FoodItemImageStorageImpl {

    private static final Logger log = LoggerFactory.getLogger(FoodItemImageStorageImpl.class);
    public final FoodItemConfig foodItemConfig;

    public FoodItemImageStorageImpl(FoodItemConfig foodItemConfig) {
        this.foodItemConfig = foodItemConfig;
    }


    public void saveImagesFiles(List<FoodItemImageDTO> foodItemImageDTOList) {
        for (FoodItemImageDTO images : foodItemImageDTOList) {
            try {
                saveImageFile(images.get_data(), images.getFileName());
            } catch (IOException e) {
                log.error("Erro ao salvar a imagem: " + images.getFileName(), e);
            }
        }
    }

    public void getImagesFiles(List<FoodItemImageDTO> foodItemImageDTOList) {
        for (FoodItemImageDTO images : foodItemImageDTOList) {
            try {
                images.set_data(getImgaeData(images.getFileName()));
            } catch (IOException e) {
                log.error("Erro ao buscar a imagem: " + images.getFileName(), e);
            }
        }
    }

    public void deleteImagesFiles(List<FoodItemImageDTO> foodItemImageDTOList) {
        for (FoodItemImageDTO image : foodItemImageDTOList) {
            deleteImageFile(image.getFileName());
        }
    }

    public void saveImageFile(FoodItemImageDTO foodItemImageDTO) {
        try {
            saveImageFile(foodItemImageDTO.get_data(), foodItemImageDTO.getFileName());
        } catch (IOException e) {
            log.error("Erro ao salvar a imagem: " + foodItemImageDTO.getFileName(), e);
        }
    }

    public void saveImageFile(String _data, String fileName) throws IOException {
        byte[] fileData = Base64.getDecoder().decode(_data);

        File directory = new File(foodItemConfig.getImage().getDirectory());
        if (!directory.exists()) {
            directory.mkdirs();
        }
        File file = new File(directory, fileName);
        try (FileOutputStream fos = new FileOutputStream(file)) {
            log.info("Salvando arquivo: {}", file.getName());
            fos.write(fileData);
        } catch (IOException e) {
            throw new IOException("Erro ao salvar o arquivo" + file.getName(), e);
        }
    }

    public void deleteImageFile(String fileName) {
        File file = new File(foodItemConfig.getImage().getDirectory() + fileName);
        if (file.exists()) {
            log.info("Deletando arquivo: {}", file.getName());
            file.delete();
        }
    }

    public String getImgaeData(String fileName) throws IOException {
        File file = new File(foodItemConfig.getImage().getDirectory() + fileName);
        if (file.exists()) {
            byte[] fileData = new byte[(int) file.length()];
            try (FileInputStream fis = new FileInputStream(file)) {
                log.info("Buscando arquivo: {}", file.getName());
                fis.read(fileData);
            } catch (IOException e) {
                throw new IOException("Erro ao obter o arquivo" + file.getName(), e);
            }
            return Base64.getEncoder().encodeToString(fileData);
        } else {
            throw new FoodItemException("Arquivo não encontrado", 404);
        }
    }
}
