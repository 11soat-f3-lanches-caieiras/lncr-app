package br.com.tp.lanchescaieiras._core.commons.interfaces.customerorder;

import br.com.tp.lanchescaieiras._core.commons.dtos.customerorder.CustomerOrderDTO;
import br.com.tp.lanchescaieiras._external.dataproxy.CustomerOrderDataProxy;

public interface CustomerOrderController {

    CustomerOrderDTO create(CustomerOrderDataProxy customerOrderDataProxy, CustomerOrderDTO customerOrderDTO);

    /*CustomerOrderDTO getCustomerOrderById(Integer id, Boolean includeFoodItems);

    List<CustomerOrderDTO> getCustomerOrderByStatus(String status, Boolean includeFoodItems);

    List<CustomerOrderDTO> getAllCustomerOrders(String status, Boolean includeFoodItems);

    CustomerOrderDTO updateOrderStatusById(Integer id, String newStatus, Boolean forceUpdate);*/



}
