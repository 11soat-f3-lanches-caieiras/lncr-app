package br.com.tp.lncr.app.datasources.postgres.oauth;

import br.com.tp.lncr.app.datasources.postgres.customer.JpaCustomerRepository;
import br.com.tp.lncr.core.commons.dtos.oauth.OauthCredentialsDTO;
import br.com.tp.lncr.core.commons.interfaces.oauth.OauthDatabase;
import org.springframework.stereotype.Repository;

@Repository
public class JpaOauthCustomerRepositoryImpl implements OauthDatabase {

    private final JpaCustomerRepository jpaCustomerRepository;
    private final JpaOauthMapper jpaCustomerMapper;

    public JpaOauthCustomerRepositoryImpl(JpaCustomerRepository jpaCustomerRepository, JpaOauthMapper jpaCustomerMapper) {
        this.jpaCustomerRepository = jpaCustomerRepository;
        this.jpaCustomerMapper = jpaCustomerMapper;
    }

    @Override
    public OauthCredentialsDTO validateCredentials(OauthCredentialsDTO oauthCredentialsDTO) {
        return jpaCustomerMapper.jpaCustomerToDTO(jpaCustomerRepository.findByDocumentNumberAndEmail(oauthCredentialsDTO.client_id(),oauthCredentialsDTO.client_secret()).orElse(null));
    }

}
