package br.com.tp.lanchescaieiras.customer.external.datasources.postgres;

import br.com.tp.lanchescaieiras.commons.dtos.CustomerDTO;

public class JpaCustomerPostgresMapper {

    public JpaCustomerPostgresEntity toJpaCustomerPostgresEntity(CustomerDTO customerDTO){
        if (customerDTO == null) {
            return null;
        }
        return new JpaCustomerPostgresEntity(
        customerDTO.getId(),
        customerDTO.getDocumentNumber(),
        customerDTO.getName(),
        customerDTO.getEmail());

    }

    public CustomerDTO toCustomerDTO(JpaCustomerPostgresEntity jpaCustomerPostgresEntity){
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












