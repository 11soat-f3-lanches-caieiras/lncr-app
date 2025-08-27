package br.com.tp.lncr.core.commons.dtos.oauth;

import br.com.tp.lncr.core.commons.interfaces.oauth.OauthDatabase;

public class OauthProfileConfig implements OauthDatabase {
    private static final Integer EXPIRE_IN = 3600;
    private static final String TOKEN_TYPE = "Bearer";
    private final String secretKey;
    private final OauthProfileDTO oauthProfile;

    public OauthProfileConfig(String secretKey, OauthProfileDTO oauthProfile) {
        this.secretKey = secretKey;
        this.oauthProfile = oauthProfile;
    }

    public OauthProfileDTO getOauthProfile() {
        return oauthProfile;
    }

    public Integer getExpireIn() {
        return EXPIRE_IN;
    }

    public String getTokenType() {
        return TOKEN_TYPE;
    }

    public String getSecretKey() {
        return secretKey;
    }

    @Override
    public OauthCredentialsDTO validateCredentials(String client_id, String client_secret) {
        if (oauthProfile.getClientId().equals(client_id) && oauthProfile.getClientSecret().equals(client_secret)) {
            return new OauthCredentialsDTO(
                    client_id,
                    client_secret,
                    oauthProfile.getGrantType(),
                    oauthProfile.getScope(),
                    null,
                    null
            );
        }
        return null;
    }


}
