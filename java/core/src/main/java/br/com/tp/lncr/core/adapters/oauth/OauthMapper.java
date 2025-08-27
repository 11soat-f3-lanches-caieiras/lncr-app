package br.com.tp.lncr.core.adapters.oauth;

import br.com.tp.lncr.core.commons.dtos.oauth.OauthTokenDTO;
import br.com.tp.lncr.core.domain.oauth.OauthToken;

public class OauthMapper {

    public OauthTokenDTO toOauthTokenDTO(OauthToken oauthToken) {
        return new OauthTokenDTO(
                oauthToken.getAccessToken(),
                oauthToken.getTokenType(),
                oauthToken.getExpiresIn()
        );
    }
}
