package br.com.tp.lncr.core.commons.interfaces.oauth;

import br.com.tp.lncr.core.commons.dtos.oauth.OauthCredentialsDTO;
import br.com.tp.lncr.core.commons.dtos.oauth.OauthProfileConfig;

public interface OauthGateway {

    OauthCredentialsDTO validateCredentials(String authorization, OauthCredentialsDTO oauthCredentialsDTO, OauthProfileStrategy oauthProfileStrategy, OauthProfileConfig oauthProfileConfig);
}
