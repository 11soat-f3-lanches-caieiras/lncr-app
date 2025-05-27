package br.com.tp.lanchescaieiras.customerorder.adapters.inbound.controllers;

import br.com.tp.lanchescaieiras.customerorder.domain.CustomerOrder;
import br.com.tp.lanchescaieiras.customerorder.domain.CustomerOrderListResponse;
import br.com.tp.lanchescaieiras.customerorder.domain.CustomerOrderResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface CustomerOrderController {

    public ResponseEntity<CustomerOrderResponse> createCustomerOrder(@RequestBody CustomerOrder customerOrder);

    public ResponseEntity<CustomerOrderResponse> getCustomerOrderById(@PathVariable Integer id,
                                                                      @RequestParam(name = "includeFoodItems", required = false, defaultValue = "false") Boolean includeFoodItems);

    public ResponseEntity<CustomerOrderListResponse> getCustomerOrderByStatus(@PathVariable String status,
                                                                              @RequestParam(name = "includeFoodItems", required = false, defaultValue = "false") Boolean includeFoodItems);

    public ResponseEntity<CustomerOrderResponse> updateOrderStatusById(@PathVariable Integer id,
                                                                       @PathVariable String newStatus, @RequestParam Boolean forceUpdate);

}
