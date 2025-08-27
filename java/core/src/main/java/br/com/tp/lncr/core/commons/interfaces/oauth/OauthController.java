package br.com.tp.lncr.core.commons.interfaces.oauth;

import br.com.tp.lncr.core.commons.dtos.oauth.OauthCredentialsDTO;
import br.com.tp.lncr.core.commons.dtos.oauth.OauthProfileConfig;
import br.com.tp.lncr.core.commons.dtos.oauth.OauthTokenDTO;

public interface OauthController {

    OauthTokenDTO createToken(String authorization, OauthCredentialsDTO oauthCredentialsDTO, OauthProfileConfig oauthProfileConfig, OauthProfileStrategy oauthProfileStrategy);
}
