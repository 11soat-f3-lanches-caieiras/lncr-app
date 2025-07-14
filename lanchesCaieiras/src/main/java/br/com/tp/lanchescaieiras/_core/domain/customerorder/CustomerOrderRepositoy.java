package br.com.tp.lanchescaieiras._core.domain.customerorder;

import java.util.List;

public interface CustomerOrderRepositoy {

    CustomerSort save(CustomerSort customerOrder);

    CustomerSort findById(Integer id);

    List<CustomerSort> findByStatusId(Integer statusId);

    CustomerSort updateStatusById(Integer id, Integer statusId);
}
