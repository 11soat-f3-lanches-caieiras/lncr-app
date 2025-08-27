package br.com.tp.lncr.core.adapters.oauth;

import br.com.tp.lncr.core.commons.dtos.oauth.OauthTokenDTO;
import br.com.tp.lncr.core.domain.oauth.OauthToken;

public class OauthPresenter {

    private final OauthMapper oauthMapper;

    public OauthPresenter(OauthMapper oauthMapper) {
        this.oauthMapper = oauthMapper;
    }


    public OauthTokenDTO createdToken(OauthToken oauthToken) {
        return oauthMapper.toOauthTokenDTO(oauthToken);
    }
}
