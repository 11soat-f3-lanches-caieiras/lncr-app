package br.com.tp.lncr.core.commons.interfaces.oauth;

import br.com.tp.lncr.core.commons.dtos.oauth.OauthCredentialsDTO;

public interface OauthGateway {

    OauthCredentialsDTO validateCredentials(OauthCredentialsDTO oauthCredentialsDTO);
}
