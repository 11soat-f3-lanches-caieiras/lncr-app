package br.com.tp.lanchescaieiras._external.datasources.postgres.customerorder;


import br.com.tp.lanchescaieiras._core.commons.dtos.customerorder.CustomerOrderDTO;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JpaCustomerOrderPostgresRepositoryImpl{

    private final JpaCustomerOrderPostgresRepository jpaCustomerOrderPostgresRepository;
    private final JpaCustomerOrderPostgresMapper jpaCustomerOrderPostgresMapper;

    public JpaCustomerOrderPostgresRepositoryImpl(@Lazy JpaCustomerOrderPostgresRepository jpaCustomerOrderPostgresRepository, JpaCustomerOrderPostgresMapper jpaCustomerOrderPostgresMapper) {
        this.jpaCustomerOrderPostgresRepository = jpaCustomerOrderPostgresRepository;
        this.jpaCustomerOrderPostgresMapper = jpaCustomerOrderPostgresMapper;
    }

    public CustomerOrderDTO save(CustomerOrderDTO customerOrderDTO){
        JpaCustomerOrderPostgresEntity jpaCustomerOrderPostgresEntity = jpaCustomerOrderPostgresMapper.customerOrderDtoToJpa(customerOrderDTO);
        return jpaCustomerOrderPostgresMapper.jpaCustomerOrderToDTO(this.jpaCustomerOrderPostgresRepository.save(jpaCustomerOrderPostgresEntity));
    }


    public CustomerOrderDTO findById(Integer customerOrderId) {
        return this.jpaCustomerOrderPostgresRepository.findById(customerOrderId)
                .map(jpaCustomerOrderPostgresMapper::jpaCustomerOrderToDTO)
                .orElse(null);
    }

    public List<CustomerOrderDTO> findByStatusList(List<Integer> statusList) {
       List<JpaCustomerOrderPostgresEntity> jpaCustomerOrderList =  jpaCustomerOrderPostgresRepository.findByStatusListIds(statusList);
       return jpaCustomerOrderList.stream().map(jpaCustomerOrderPostgresMapper::jpaCustomerOrderToDTO).toList();

    }
}
