package br.com.tp.lncr.app.commons.implementations.oauth;

import br.com.tp.lncr.core.commons.interfaces.oauth.AbstractOauthProfileStrategy;
import br.com.tp.lncr.core.commons.interfaces.oauth.OauthDatabase;

public class KitchenOauthProfileStrategy extends AbstractOauthProfileStrategy {

    public KitchenOauthProfileStrategy(OauthDatabase oauthDatabase) {
        super(oauthDatabase);
    }
}
