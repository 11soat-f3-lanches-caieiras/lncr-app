package br.com.tp.lncr.core.adapters.oauth;

import br.com.tp.lncr.core.commons.dtos.oauth.OauthCredentialsDTO;
import br.com.tp.lncr.core.commons.dtos.oauth.OauthProfileConfig;
import br.com.tp.lncr.core.commons.interfaces.oauth.OauthGateway;
import br.com.tp.lncr.core.commons.interfaces.oauth.OauthProfileStrategy;
import br.com.tp.lncr.core.commons.utils.OauthUtil;

public class OauthGatewayImpl implements OauthGateway {

    @Override
    public OauthCredentialsDTO validateCredentials(String authorization, OauthCredentialsDTO oauthCredentialsDTO, OauthProfileStrategy oauthProfileStrategy, OauthProfileConfig oauthProfileConfig) {
        return oauthProfileStrategy.validateCredentials(OauthUtil.getCredentialsFromAuthorizationHeader(authorization, oauthCredentialsDTO));
    }
}
