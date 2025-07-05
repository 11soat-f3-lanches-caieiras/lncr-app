package br.com.tp.lanchescaieiras._external.datasources.postgres.customer;

import br.com.tp.lanchescaieiras._core.commons.dtos.customer.CustomerDTO;

public class JpaCustomerPostgresMapper {

    public JpaCustomerPostgresEntity toJpaCustomerPostgresEntity(CustomerDTO customerDTO) {
        if (customerDTO == null) {
            return null;
        }
        return new JpaCustomerPostgresEntity(
                customerDTO.getId(),
                customerDTO.getDocumentNumber(),
                customerDTO.getName(),
                customerDTO.getEmail());

    }

    public CustomerDTO toCustomerDTO(JpaCustomerPostgresEntity jpaCustomerPostgresEntity) {
        if (jpaCustomerPostgresEntity == null) {
            return null;
        }
        return new CustomerDTO(
                jpaCustomerPostgresEntity.getId(),
                jpaCustomerPostgresEntity.getDocumentNumber(),
                jpaCustomerPostgresEntity.getName(),
                jpaCustomerPostgresEntity.getEmail());
    }
}












