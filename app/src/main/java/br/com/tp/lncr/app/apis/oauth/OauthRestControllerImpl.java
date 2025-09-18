package br.com.tp.lncr.app.apis.oauth;

import br.com.tp.lncr.app.commons.implementations.oauth.OauthProfileStrategyFactory;
import br.com.tp.lncr.app.configs.OauthConfig;
import br.com.tp.lncr.app.datasources.postgres.oauth.JpaOauthCustomerRepositoryImpl;
import br.com.tp.lncr.core.adapters.oauth.OauthControllerImpl;
import br.com.tp.lncr.core.commons.dtos.oauth.OauthCredentialsDTO;
import br.com.tp.lncr.core.commons.dtos.oauth.OauthProfileConfig;
import br.com.tp.lncr.core.commons.dtos.oauth.OauthTokenDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/oauth")
public class OauthRestControllerImpl implements OauthRestController {

    private final OauthControllerImpl oauthController;
    private final OauthConfig oauthConfig;
    private final JpaOauthCustomerRepositoryImpl oauthCustomerRepository;

    public OauthRestControllerImpl(OauthControllerImpl oauthController, OauthConfig oauthConfig, JpaOauthCustomerRepositoryImpl oauthCustomerRepository) {
        this.oauthController = oauthController;
        this.oauthConfig = oauthConfig;
        this.oauthCustomerRepository = oauthCustomerRepository;
    }

    @Override
    @PostMapping("/token")
    public ResponseEntity<OauthTokenDTO> token(@RequestHeader("Authorization") String authorizationHeader,
                                               @RequestBody OauthCredentialsDTO body) {
        OauthProfileConfig oauthProfileConfig = oauthConfig.getProfileConfig(body.scope());
        OauthTokenDTO oauthTokenDTO = oauthController.createToken(authorizationHeader, body, oauthProfileConfig, new OauthProfileStrategyFactory(oauthCustomerRepository,oauthProfileConfig).getStrategy(body.scope()));
        return ResponseEntity.ok(oauthTokenDTO);
    }
}
