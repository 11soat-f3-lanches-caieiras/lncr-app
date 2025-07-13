package br.com.tp.lanchescaieiras._external.datasources.postgres.customer;

import br.com.tp.lanchescaieiras._core.commons.dtos.customer.CustomerDTO;
import org.springframework.stereotype.Component;

@Component
public class JpaCustomerMapper {

    public JpaCustomerEntity toJpaCustomerPostgresEntity(CustomerDTO customerDTO) {
        if (customerDTO == null) {
            return null;
        }
        return new JpaCustomerEntity(
                customerDTO.getId(),
                customerDTO.getDocumentNumber(),
                customerDTO.getName(),
                customerDTO.getEmail());

    }

    public CustomerDTO toCustomerDTO(JpaCustomerEntity jpaCustomerEntity) {
        if (jpaCustomerEntity == null) {
            return null;
        }
        return new CustomerDTO(
                jpaCustomerEntity.getId(),
                jpaCustomerEntity.getDocumentNumber(),
                jpaCustomerEntity.getName(),
                jpaCustomerEntity.getEmail());
    }
}












