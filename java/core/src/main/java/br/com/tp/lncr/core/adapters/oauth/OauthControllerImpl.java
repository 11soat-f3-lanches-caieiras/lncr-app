package br.com.tp.lncr.core.adapters.oauth;

import br.com.tp.lncr.core.applications.oauth.CreateTokenUseCase;
import br.com.tp.lncr.core.commons.dtos.oauth.OauthCredentialsDTO;
import br.com.tp.lncr.core.commons.dtos.oauth.OauthProfileConfig;
import br.com.tp.lncr.core.commons.dtos.oauth.OauthTokenDTO;
import br.com.tp.lncr.core.commons.interfaces.oauth.OauthController;
import br.com.tp.lncr.core.commons.interfaces.oauth.OauthProfileStrategy;
import br.com.tp.lncr.core.domain.oauth.OauthToken;


public class OauthControllerImpl implements OauthController {

    private final OauthMapper oauthMapper = new OauthMapper();

    @Override
    public OauthTokenDTO createToken(String authorization, OauthCredentialsDTO oauthCredentialsDTO, OauthProfileConfig oauthProfileConfig, OauthProfileStrategy oauthProfileStrategy) {
        OauthGatewayImpl oauthGateway = new OauthGatewayImpl(oauthProfileStrategy);
        OauthToken oauthToken = new CreateTokenUseCase(oauthGateway).createToken(authorization, oauthCredentialsDTO, oauthProfileConfig);
        return new OauthPresenter(oauthMapper).createdToken(oauthToken);

    }


}
