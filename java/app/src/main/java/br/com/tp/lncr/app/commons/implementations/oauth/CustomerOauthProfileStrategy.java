package br.com.tp.lncr.app.commons.implementations.oauth;

import br.com.tp.lncr.core.commons.interfaces.oauth.AbstractOauthProfileStrategy;
import br.com.tp.lncr.core.commons.interfaces.oauth.OauthDatabase;

public class CustomerOauthProfileStrategy extends AbstractOauthProfileStrategy {

    public CustomerOauthProfileStrategy(OauthDatabase oauthDatabase) {
        super(oauthDatabase);
    }
}


