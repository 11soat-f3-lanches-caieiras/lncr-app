package br.com.tp.lanchescaieiras._core.domain.customerorder;

import java.util.List;

public interface CustomerOrderRepositoy {

    CustomerOrder save(CustomerOrder customerOrder);

    CustomerOrder findById(Integer id);

    List<CustomerOrder> findByStatusId(Integer statusId);

    CustomerOrder updateStatusById(Integer id, Integer statusId);
}
