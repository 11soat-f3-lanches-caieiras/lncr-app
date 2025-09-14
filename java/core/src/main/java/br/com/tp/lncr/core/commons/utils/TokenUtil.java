package br.com.tp.lncr.core.commons.utils;

import br.com.tp.lncr.core.commons.dtos.oauth.OauthCredentialsDTO;
import br.com.tp.lncr.core.commons.dtos.oauth.OauthProfileConfig;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TokenUtil {
    public static String generateToken(OauthCredentialsDTO oauthCredentialsDTO, OauthProfileConfig oauthConfig) {
        return JWT.create()
                .withSubject("lncr-token")
                .withExpiresAt(getExpirationDate(oauthConfig.getExpireIn()))
                .withIssuedAt(new Date())
                .withPayload(setClaims(oauthCredentialsDTO))
                .sign(getAlgorithm(oauthConfig.getSecretKey()));
    }

    private static Date getExpirationDate(Integer expireIn) {
        Date now = new Date();
        return new Date(now.getTime() + (expireIn * 1000));
    }

    private static Algorithm getAlgorithm(String secretkey) {
        return Algorithm.HMAC256(secretkey);
    }
    private static Map<String,Object> setClaims(OauthCredentialsDTO oauthCredentialsDTO){
        Map<String, Object> claims = new HashMap<>();
        claims.put("client_id", oauthCredentialsDTO.client_id());
        claims.put("scope", oauthCredentialsDTO.scope());
        claims.put("name", oauthCredentialsDTO.name());
        claims.put("customerId", oauthCredentialsDTO.customerId());
        return claims;
    }
}
