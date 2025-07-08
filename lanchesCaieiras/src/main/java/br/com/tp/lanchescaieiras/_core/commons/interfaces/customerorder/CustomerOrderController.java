package br.com.tp.lanchescaieiras._core.commons.interfaces.customerorder;

import br.com.tp.lanchescaieiras._core.commons.dtos.customerorder.CustomerOrderDTO;

import java.util.List;

public interface CustomerOrderController {

    CustomerOrderDTO create(CustomerOrderDatabase customerOrderDatabase, CustomerOrderDTO customerOrderDTO);

    CustomerOrderDTO getById(CustomerOrderDatabase customerOrderDatabase, Integer customerOrderId, Boolean includeFoodItems);

    List<CustomerOrderDTO> getByStatusList(CustomerOrderDatabase customerOrderDatabase, List<String> statusList, Boolean includeFoodItems);

    CustomerOrderDTO updateStatusById(CustomerOrderDatabase customerOrderDatabase, Integer customerOrderId, String newStatus, Boolean forceUpdate);

    /*CustomerOrderDTO getCustomerOrderById(Integer id, Boolean includeFoodItems);

    List<CustomerOrderDTO> getCustomerOrderByStatus(String status, Boolean includeFoodItems);

    List<CustomerOrderDTO> getAllCustomerOrders(String status, Boolean includeFoodItems);

    CustomerOrderDTO updateOrderStatusById(Integer id, String newStatus, Boolean forceUpdate);*/



}
