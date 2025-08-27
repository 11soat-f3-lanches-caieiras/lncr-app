package br.com.tp.lncr.core.domain.oauth;

import br.com.tp.lncr.core.commons.dtos.oauth.OauthCredentialsDTO;
import br.com.tp.lncr.core.commons.dtos.oauth.OauthProfileConfig;
import br.com.tp.lncr.core.commons.utils.TokenUtil;

public class OauthToken {
    private final String accessToken;
    private final String tokenType;
    private final Integer expiresIn;

    public OauthToken(OauthCredentialsDTO credentialsDto, OauthProfileConfig profileConfig) {
        this.tokenType = profileConfig.getTokenType();
        this.expiresIn = profileConfig.getExpireIn();
        this.accessToken = TokenUtil.generateToken(credentialsDto, profileConfig);
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public Integer getExpiresIn() {
        return expiresIn;
    }

}
