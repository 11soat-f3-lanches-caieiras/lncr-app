package br.com.tp.lncr.core.applications.oauth;

import br.com.tp.lncr.core.commons.dtos.oauth.OauthCredentialsDTO;
import br.com.tp.lncr.core.commons.dtos.oauth.OauthProfileConfig;
import br.com.tp.lncr.core.commons.interfaces.oauth.OauthGateway;
import br.com.tp.lncr.core.commons.interfaces.oauth.OauthProfileStrategy;
import br.com.tp.lncr.core.domain.oauth.OauthToken;

public class CreateTokenUseCase {

    private final OauthGateway oauthGateway;

    public CreateTokenUseCase(OauthGateway oauthGateway) {
        this.oauthGateway = oauthGateway;
    }

    public OauthToken createToken(String authorization, OauthCredentialsDTO oauthCredentialsDTO, OauthProfileConfig oauthProfileConfig, OauthProfileStrategy oauthProfileStrategy) {
        oauthCredentialsDTO = oauthGateway.validateCredentials(authorization, oauthCredentialsDTO, oauthProfileStrategy, oauthProfileConfig);
        return new OauthToken(oauthCredentialsDTO, oauthProfileConfig);
    }
}