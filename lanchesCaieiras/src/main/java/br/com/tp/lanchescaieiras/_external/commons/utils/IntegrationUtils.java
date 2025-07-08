package br.com.tp.lanchescaieiras._external.commons.utils;

import br.com.tp.lanchescaieiras._external.integrations.IntegrationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;



public class IntegrationUtils {

    public static <T> T getIntegrationContent(String body, Class<T> classType){
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode root = mapper.readTree(body);
            JsonNode contentNode = root.path("_content");
            return mapper.treeToValue(contentNode, classType);
        } catch (JsonProcessingException e) {
            throw new IntegrationException("Erro ao mapear conteúdo na integração: " + classType.getName(),500);
        }
    }

    public static <T> T getIntegrationContentList(String body, TypeReference<T> typeReference){
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode root = mapper.readTree(body);
            JsonNode contentNode = root.path("_content");
            return mapper.readValue(contentNode.traverse(), typeReference);
        } catch (Exception e) {
            throw new IntegrationException("Erro ao mapear conteúdo na integração: " + typeReference.getType(), 500);
        }
    }
}
