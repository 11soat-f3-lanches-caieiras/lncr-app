package br.com.tp.lncr.core.commons.dtos.oauth;

@SuppressWarnings("unused")
public class OauthProfileDTO {
    private String clientId;
    private String clientSecret;
    private String scope;
    private String grantType;


    public OauthProfileDTO() {
    }

    public OauthProfileDTO(String clientId, String clientSecret, String scope, String grantType) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.scope = scope;
        this.grantType = grantType;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getGrantType() {
        return grantType;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }

    @Override
    public String toString() {
        return "OauthProfileDTO{" +
                "clientId='" + clientId + '\'' +
                ", clientSecret='" + clientSecret + '\'' +
                ", scope='" + scope + '\'' +
                ", grantType='" + grantType + '\'' +
                '}';
    }
}
