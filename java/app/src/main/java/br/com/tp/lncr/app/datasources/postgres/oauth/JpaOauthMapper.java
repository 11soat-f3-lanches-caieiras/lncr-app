package br.com.tp.lncr.app.datasources.postgres.oauth;

import br.com.tp.lncr.app.datasources.postgres.customer.JpaCustomerEntity;
import br.com.tp.lncr.core.commons.dtos.oauth.OauthCredentialsDTO;

public class JpaOauthMapper {

    public JpaOauthMapper() {
    }

    OauthCredentialsDTO jpaCustomerToDTO(JpaCustomerEntity jpaCustomerEntity){
        if(jpaCustomerEntity == null) return null;
        return new OauthCredentialsDTO(
                jpaCustomerEntity.getDocumentNumber(),
                jpaCustomerEntity.getEmail(),
            null,
            null,
                jpaCustomerEntity.getName(),
                jpaCustomerEntity.getId()
        );
    }
}
