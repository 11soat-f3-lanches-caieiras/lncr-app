package br.com.tp.lanchescaieiras._external.commons.utils;

import br.com.tp.lanchescaieiras._external.integrations.IntegrationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

public class IntegrationUtil {

    private static final Logger log = LoggerFactory.getLogger(IntegrationUtil.class);

    private static <T> HttpEntity<T> setRequestEntity(T dto){
        return new HttpEntity<T>(dto, setHeaders());
    }

    private static HttpHeaders setHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        return headers;
    }

    public static <T> String toJson(T dto) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(dto);
        } catch (JsonProcessingException e) {
            log.error("Erro ao converter "+ dto.getClass().getSimpleName() + " para JSON: {}", e.getMessage());
            throw new IntegrationException("Erro ao converter "+ dto.getClass().getSimpleName() + " para JSON", 500);
        }
    }

    public static <T> void  postForObject(String url, T dto) {
        RestTemplate restTemplate = new RestTemplate();
        CompletableFuture.runAsync(() -> {
       try {
                restTemplate.postForObject(url, IntegrationUtil.setRequestEntity(dto), String.class);
            } catch (Exception e) {
                throw new IntegrationException("Erro na integração com", 500);
            }
        });
    }
}
