package br.com.tp.lncr.app.configs;

import br.com.tp.lncr.app.datasources.postgres.oauth.JpaOauthMapper;
import br.com.tp.lncr.core.adapters.oauth.OauthControllerImpl;
import br.com.tp.lncr.core.commons.dtos.oauth.OauthProfileConfig;
import br.com.tp.lncr.core.commons.dtos.oauth.OauthProfileDTO;
import br.com.tp.lncr.core.commons.exceptions.OauthException;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@SuppressWarnings("unused")
@Component
@ConfigurationProperties(prefix = "lncr.oauth")
public class OauthConfig {
    private String secretKey;
    private Map<String, OauthProfileDTO> profiles;

    public Map<String, OauthProfileDTO> getProfiles() {
        return profiles;
    }

    public void setProfiles(Map<String, OauthProfileDTO> profiles) {
        this.profiles = profiles;
    }

    public List<String> getAllScopes() {
        return profiles != null ? profiles.values().stream().map(OauthProfileDTO::getScope).toList() : List.of();
    }

    public List<String> getAllGrantTypes() {
        return profiles != null ? profiles.values().stream().map(OauthProfileDTO::getGrantType).toList() : List.of();
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public OauthProfileConfig getProfileConfig(String profileKey) {
        if (profiles != null && profiles.containsKey(profileKey)) {
            return new OauthProfileConfig(secretKey, profiles.get(profileKey));
        }
        throw new OauthException("Scope inválido: " + profileKey, 400);
    }

    @Bean
    public OauthControllerImpl oauthControllerImpl(){
        return new OauthControllerImpl();
    }

    @Bean
    public JpaOauthMapper jpaOauthMapper(){
        return new JpaOauthMapper();
    }

}
