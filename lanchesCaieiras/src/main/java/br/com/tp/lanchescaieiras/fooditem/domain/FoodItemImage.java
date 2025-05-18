package br.com.tp.lanchescaieiras.fooditem.domain;

import br.com.tp.lanchescaieiras.fooditem.infraestructure.exceptions.FoodItemException;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Base64;
import java.util.Map;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class FoodItemImage {
    public Integer id;
    public String _data;
    public String location;
    public String fileName;
    public String fileExtension;

    public FoodItemImage() {
    }

    public FoodItemImage(Integer id, String _data, String location, String fileName, String fileExtension) {
        this.id = id;
        this._data = _data;
        this.location = location;
        this.fileName = fileName;
        this.fileExtension = fileExtension;
    }

    public FoodItemImage(String _data, String fileName, String fileExtension) {
        this._data = _data;
        this.fileName = fileName;
        this.fileExtension = fileExtension;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    //Método para validar imagens enviadas
    public String validateImage(String _base64, Map<String,String> imageConfig, Integer maxSizeInBytes) {
        if (validateImageSize(_base64, maxSizeInBytes)){ // Valida tamanho
            for (Map.Entry<String, String> entry : imageConfig.entrySet()) { // Lista de extensões permitidas
                String headerExtensions = entry.getValue();
                if (validateImageExtention(_base64, headerExtensions)) { // Valida se extensão é permitida
                    return entry.getKey();
                }
            }
            String allowedExtensions = String.join(", ", imageConfig.keySet());
            return  "Imagens inválidas. Extensões permitidas:" + allowedExtensions; //Adiciona mensagem de erro de extensão não permitida
        };
        return "Tamanho da imagem excede o limite de " + maxSizeInBytes + " bytes"; //Adiciona mensagem de erro de tamanho inválid para o suário
    }

    public boolean validateImageSize(String _base64, Integer maxSizeInBytes) {
        return getDecodeImageData(_base64).length <= maxSizeInBytes;

    }

    public byte[] getDecodeImageData(String _base64) {
        try {
            return Base64.getDecoder().decode(_base64);
        }catch (IllegalArgumentException e){
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
