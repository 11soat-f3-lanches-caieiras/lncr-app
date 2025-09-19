package br.com.tp.lncr.app.commons.implementations.oauth;

import br.com.tp.lncr.core.commons.interfaces.oauth.AbstractOauthProfileStrategy;
import br.com.tp.lncr.core.commons.interfaces.oauth.OauthDatabase;

public class TotemOauthProfileStrategy extends AbstractOauthProfileStrategy {
    public TotemOauthProfileStrategy(OauthDatabase oauthDatabase) {
        super(oauthDatabase);
    }
}

