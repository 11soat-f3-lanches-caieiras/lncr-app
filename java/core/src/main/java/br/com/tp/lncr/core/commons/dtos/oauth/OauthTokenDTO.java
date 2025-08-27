package br.com.tp.lncr.core.commons.dtos.oauth;

public record OauthTokenDTO(
        String accessToken,
        String tokenType,
        Integer expiresIn
) {}
