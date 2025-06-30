package br.com.tp.lanchescaieiras.fooditem.domain;

import br.com.tp.lanchescaieiras.fooditem.domain.exceptions.FoodItemException;

import br.com.tp.lanchescaieiras.fooditem.external.config.FoodItemConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Base64;
import java.util.Map;


public class FoodItemImage {
    private static final Logger log = LoggerFactory.getLogger(FoodItemImage.class);
    public Integer id;
    public Integer foodItemId;
    public String _data;
    public String location;
    public String fileName;
    public String fileExtension;
    public String imageError;

    public FoodItemImage() {
    }

    public FoodItemImage(Integer id, Integer foodItemId, String _data, String location, String fileName, String fileExtension, String imageError) {
        this.id = id;
        this.foodItemId = foodItemId;
        this._data = _data;
        this.location = location;
        this.fileName = fileName;
        this.fileExtension = fileExtension;
        this.imageError = imageError;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFoodItemId() {return foodItemId;}

    public void setFoodItemId(Integer foodItemId) {this.foodItemId = foodItemId;  }

    public String get_data() {
        return _data;
    }

    public void set_data(String _data) {
        this._data = _data;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public String getImageError() {return imageError;}

    public void setImageError(String imageError) {this.imageError = imageError;}

    //Método para validar imagens enviadas
    public void validateImage(FoodItemConfig foodItemConfig) {
        String allowedExtensions = String.join(", ", foodItemConfig.getImage().getExtensions().keySet());
        Integer maxSizeInBytes = foodItemConfig.getImage().getMaxSize();

        if (validateImageSize(this._data, maxSizeInBytes)) { // Valida tamanho
            for (Map.Entry<String, String> entry : foodItemConfig.getImage().getExtensions().entrySet()) { // Lista de extensões permitidas
                String headerExtensions = entry.getValue();
                if (validateImageExtention(this._data, headerExtensions)) { // Valida se extensão é permitida
                    this.fileExtension = entry.getKey();
                }
            }
            if (this.fileExtension == null){
                log.info("Imagens inválidas. Extensões permitidas: {}", allowedExtensions);
                this.imageError =  "Encontrada uma imagem inválida. Extensões permitidas:" + allowedExtensions; //Adiciona mensagem de erro de extensão não permitida
            }
        }
        else {
            log.info("Tamanho da imagem excede o limite de {} bytes", maxSizeInBytes);
            this.imageError = "Encontrada imagem que excede o limite de " + maxSizeInBytes + "bytes"; //Adiciona mensagem de erro de tamanho inválid para o usuário
        }
    }

    public boolean validateImageSize(String _base64, Integer maxSizeInBytes) {
        return getDecodeImageData(_base64).length <= maxSizeInBytes;
    }

    public byte[] getDecodeImageData(String _base64) {
        try {
            if (_base64 != null) {
                return Base64.getDecoder().decode(_base64);
            }
            throw new FoodItemException("Sem informações da imagem", 400);
        } catch (IllegalArgumentException e) {
            throw new FoodItemException("Erro ao decodificar a imagem. Informar um base64 válido .", 404);
        }
    }

    public boolean validateImageExtention(String _base64, String headerExtensions) {
        String header = bytesToHex(getDecodeImageData(_base64));
        return header.startsWith(headerExtensions);
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            hexString.append(String.format("%02X", b));
        }
        return hexString.toString();
    }


}
