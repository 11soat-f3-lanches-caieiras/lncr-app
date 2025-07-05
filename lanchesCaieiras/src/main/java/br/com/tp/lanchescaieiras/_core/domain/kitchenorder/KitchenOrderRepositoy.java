package br.com.tp.lanchescaieiras._core.domain.kitchenorder;

import java.util.List;

public interface KitchenOrderRepositoy {

    KitchenOrder save(KitchenOrder kitchenOrder);

    KitchenOrder findById(Integer id);

    List<KitchenOrder> findByStatusId(Integer statusId);

    KitchenOrder updateStatusById(Integer id, Integer statusId);


    KitchenOrder findByCustomerOrderId(Integer customerOrderId);
}
