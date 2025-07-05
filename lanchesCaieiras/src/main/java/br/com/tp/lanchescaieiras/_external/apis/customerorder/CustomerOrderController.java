package br.com.tp.lanchescaieiras._external.apis.customerorder;

import br.com.tp.lanchescaieiras._core.domain.customerorder.CustomerOrder;
import br.com.tp.lanchescaieiras._core.domain.customerorder.CustomerOrderListResponse;
import br.com.tp.lanchescaieiras._core.domain.customerorder.CustomerOrderResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface CustomerOrderController {

    ResponseEntity<CustomerOrderResponse> createCustomerOrder(@RequestBody CustomerOrder customerOrder);

    ResponseEntity<CustomerOrderResponse> getCustomerOrderById(@PathVariable Integer id,
                                                               @RequestParam(name = "includeFoodItems", required = false, defaultValue = "false") Boolean includeFoodItems);

    ResponseEntity<CustomerOrderListResponse> getCustomerOrderByStatus(@PathVariable String status,
                                                                       @RequestParam(name = "includeFoodItems", required = false, defaultValue = "false") Boolean includeFoodItems);

    ResponseEntity<CustomerOrderResponse> updateOrderStatusById(@PathVariable Integer id,
                                                                @PathVariable String newStatus, @RequestParam Boolean forceUpdate);

}
