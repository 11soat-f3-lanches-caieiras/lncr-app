package br.com.tp.lncr.app.datasources.postgres.customer;

import br.com.tp.lncr.core.commons.dtos.customer.CustomerDTO;
import org.springframework.stereotype.Component;

@Component
public class JpaCustomerMapper {

    public JpaCustomerEntity customerDtoToJpa(CustomerDTO customerDTO) {
        if (customerDTO == null) {
            return null;
        }
        return new JpaCustomerEntity(
                customerDTO.getId(),
                customerDTO.getDocumentNumber(),
                customerDTO.getName(),
                customerDTO.getEmail());

    }

    public CustomerDTO jpaCustomerToDTO(JpaCustomerEntity jpaCustomerEntity) {
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












