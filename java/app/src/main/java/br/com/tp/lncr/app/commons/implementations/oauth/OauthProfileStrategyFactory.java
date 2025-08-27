package br.com.tp.lncr.app.commons.implementations.oauth;

import br.com.tp.lncr.core.commons.exceptions.OauthException;
import br.com.tp.lncr.core.commons.interfaces.oauth.OauthDatabase;
import br.com.tp.lncr.core.commons.interfaces.oauth.OauthProfileStrategy;

public class OauthProfileStrategyFactory {

    private final OauthDatabase jpaOauthDatabase;
    private final OauthDatabase configurationDatabase;

    public OauthProfileStrategyFactory(OauthDatabase jpaOauthDatabase, OauthDatabase configurationDatabase) {
        this.jpaOauthDatabase = jpaOauthDatabase;
        this.configurationDatabase = configurationDatabase;
    }

    public OauthProfileStrategy getStrategy(String profile) {
        return switch (profile.toUpperCase()) {
            case "ADMIN" -> new AdminOauthProfileStrategy(configurationDatabase);
            case "TOTEM" -> new TotemOauthProfileStrategy(configurationDatabase);
            case "CUSTOMER" -> new CustomerOauthProfileStrategy(jpaOauthDatabase);
            default -> throw new OauthException("Perfil inválido", 400);
        };
    }
}