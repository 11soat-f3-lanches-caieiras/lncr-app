package br.com.tp.lncr.app.commons.implementations.oauth;

import br.com.tp.lncr.core.commons.interfaces.oauth.AbstractOauthProfileStrategy;
import br.com.tp.lncr.core.commons.interfaces.oauth.OauthDatabase;

public class AdminOauthProfileStrategy extends AbstractOauthProfileStrategy {

    public AdminOauthProfileStrategy(OauthDatabase oauthDatabase) {
        super(oauthDatabase);
    }

}

