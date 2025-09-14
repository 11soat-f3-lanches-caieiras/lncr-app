package br.com.tp.lncr.core.applications.oauth;

import br.com.tp.lncr.core.commons.dtos.oauth.OauthCredentialsDTO;
import br.com.tp.lncr.core.commons.dtos.oauth.OauthProfileConfig;
import br.com.tp.lncr.core.commons.interfaces.oauth.OauthGateway;
import br.com.tp.lncr.core.commons.utils.OauthUtil;
import br.com.tp.lncr.core.domain.oauth.OauthToken;

public class CreateTokenUseCase {

    private final OauthGateway oauthGateway;

    public CreateTokenUseCase(OauthGateway oauthGateway) {
        this.oauthGateway = oauthGateway;
    }

    public OauthToken createToken(String authorization, OauthCredentialsDTO oauthCredentialsDTO, OauthProfileConfig oauthProfileConfig) {
        OauthUtil.validateTokenRequest(authorization, oauthCredentialsDTO, oauthProfileConfig);
        oauthCredentialsDTO = OauthUtil.getCredentialsFromAuthorizationHeader(authorization, oauthCredentialsDTO);
        oauthCredentialsDTO = oauthGateway.validateCredentials(oauthCredentialsDTO);
        return new OauthToken(oauthCredentialsDTO, oauthProfileConfig);
    }
}