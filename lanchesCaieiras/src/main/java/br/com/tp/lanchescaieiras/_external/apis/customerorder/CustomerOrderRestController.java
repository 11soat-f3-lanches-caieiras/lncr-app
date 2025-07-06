package br.com.tp.lanchescaieiras._external.apis.customerorder;

import br.com.tp.lanchescaieiras._core.commons.dtos.customerorder.CustomerOrderDTO;
import br.com.tp.lanchescaieiras._external.commons.model.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface CustomerOrderRestController {

    ResponseEntity<Response<CustomerOrderDTO>> createCustomerOrder(@RequestBody CustomerOrderDTO customerOrderDTO);

    /*ResponseEntity<CustomerOrderResponse> getCustomerOrderById(@PathVariable Integer id,
                                                               @RequestParam(name = "includeFoodItems", required = false, defaultValue = "false") Boolean includeFoodItems);

    ResponseEntity<CustomerOrderListResponse> getCustomerOrderByStatus(@PathVariable String status,
                                                                       @RequestParam(name = "includeFoodItems", required = false, defaultValue = "false") Boolean includeFoodItems);

    ResponseEntity<CustomerOrderResponse> updateOrderStatusById(@PathVariable Integer id,
                                                                @PathVariable String newStatus, @RequestParam Boolean forceUpdate);*/

}
