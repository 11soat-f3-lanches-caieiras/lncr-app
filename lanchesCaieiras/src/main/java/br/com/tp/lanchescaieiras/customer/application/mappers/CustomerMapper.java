package br.com.tp.lanchescaieiras.customer.application.mappers;

import br.com.tp.lanchescaieiras.customer.adapters.outbound.entities.JpaCustomerEntity;
import br.com.tp.lanchescaieiras.customer.domain.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    @Mappings({
            @Mapping(target = "id", source = "jpaCustomerEntity.id"),
            @Mapping(target = "documentNumber", source = "jpaCustomerEntity.documentNumber"),
            @Mapping(target = "name", source = "jpaCustomerEntity.name"),
            @Mapping(target = "email", source = "jpaCustomerEntity.email")
            })
    Customer jpaToDomain(JpaCustomerEntity jpaCustomerEntity);

    @Mappings({
            @Mapping(target = "id", source = "customer.id"),
            @Mapping(target = "documentNumber", source = "customer.documentNumber"),
            @Mapping(target = "name", source = "customer.name"),
            @Mapping(target = "email", source = "customer.email")
            })
    JpaCustomerEntity domainToJpa(Customer customer);
}





