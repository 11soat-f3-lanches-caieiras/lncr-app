package br.com.tp.lncr.core.commons.interfaces.oauth;

import br.com.tp.lncr.core.commons.dtos.oauth.OauthCredentialsDTO;

public interface OauthDatabase {

    OauthCredentialsDTO validateCredentials(OauthCredentialsDTO oauthCredentialsDTO);

}
