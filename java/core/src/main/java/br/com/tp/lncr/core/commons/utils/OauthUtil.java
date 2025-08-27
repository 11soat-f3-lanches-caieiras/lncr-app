package br.com.tp.lncr.core.commons.utils;

import br.com.tp.lncr.core.commons.dtos.oauth.OauthCredentialsDTO;
import br.com.tp.lncr.core.commons.dtos.oauth.OauthProfileConfig;
import br.com.tp.lncr.core.commons.exceptions.OauthException;

@SuppressWarnings("unused")
public class OauthUtil {

    private static final String AUTHORIZATION_TYPE = "Basic ";

    public static OauthCredentialsDTO getCredentialsFromAuthorizationHeader(String authorizationHeader, OauthCredentialsDTO body) {
        String base64Credentials = authorizationHeader.substring(AUTHORIZATION_TYPE.length());
        String credentials = new String(java.util.Base64.getDecoder().decode(base64Credentials));
        String[] values = credentials.split(":", 2);
        String clientId = values[0];
        String clientSecret = values[1];
        return new OauthCredentialsDTO(clientId, clientSecret, body.grant_type(), body.scope(), body.name(), body.customerId());
    }

    private static void validadeAuthorizationHeader(String authorizationHeader, OauthProfileConfig oauthConfig) {
        if (authorizationHeader == null || !authorizationHeader.startsWith(AUTHORIZATION_TYPE)) {
            throw new OauthException("Authorization inválido", 400);
        }
    }

    private static void validadeBody(OauthCredentialsDTO body, OauthProfileConfig oauthConfig) {
        validateGrantType(body,oauthConfig);
        validateScope(body,oauthConfig);
    }

    private static void validateGrantType(OauthCredentialsDTO body, OauthProfileConfig oauthConfig) {
        if (body == null || body.grant_type() == null || body.grant_type().isEmpty()) {
            throw new OauthException("grant_type é obrigatório", 400);
        }
        String grantType = body.grant_type();
        if (!oauthConfig.getOauthProfile().getGrantType().equals(grantType)) {
            throw new OauthException("grant_type: "+ grantType +" inválido", 400);
        }
    }

    private static void validateScope(OauthCredentialsDTO body, OauthProfileConfig oauthConfig) {
    if (body.scope() == null || body.scope().isEmpty()) {
        throw new OauthException("scope é obrigatório", 400);
    }
    String scope = body.scope();
    if (!oauthConfig.getOauthProfile().getScope().equals(scope)) {
        throw new OauthException("Scope: "+ scope +" inválido", 400);
    }

}

public static void validateTokenRequest(String authorizationHeader, OauthCredentialsDTO body, OauthProfileConfig oauthConfig) {
    validadeAuthorizationHeader(authorizationHeader,oauthConfig);
    validadeBody(body,oauthConfig);
}
}
