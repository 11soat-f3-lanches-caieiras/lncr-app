package br.com.tp.lncr.app.apis.oauth;

import br.com.tp.lncr.core.commons.dtos.oauth.OauthCredentialsDTO;
import br.com.tp.lncr.core.commons.dtos.oauth.OauthTokenDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@SuppressWarnings("unused")
public interface OauthRestController {

    ResponseEntity<OauthTokenDTO> token(@RequestHeader("Authorization") String authorizationHeader,
                                        @RequestBody OauthCredentialsDTO body);


}
