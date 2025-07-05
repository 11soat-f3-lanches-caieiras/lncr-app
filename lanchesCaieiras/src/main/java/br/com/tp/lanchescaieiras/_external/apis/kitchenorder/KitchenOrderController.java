package br.com.tp.lanchescaieiras._external.apis.kitchenorder;

import br.com.tp.lanchescaieiras._core.domain.kitchenorder.KitchenOrder;
import br.com.tp.lanchescaieiras._core.domain.kitchenorder.KitchenOrderListResponse;
import br.com.tp.lanchescaieiras._core.domain.kitchenorder.KitchenOrderResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface KitchenOrderController {

    ResponseEntity<KitchenOrderResponse> createKitchenOrder(@RequestBody KitchenOrder kitchenOrder);

    ResponseEntity<KitchenOrderResponse> getKitchenOrderById(@PathVariable Integer id,
                                                             @RequestParam(name = "includeFoodItems", required = false, defaultValue = "false") Boolean includeFoodItems);


    ResponseEntity<KitchenOrderResponse> getKitchenOrderByCustomerOrderId(@PathVariable(name = "customerOrderId") Integer customerOrderId,
                                                                          @RequestParam(name = "includeFoodItems", required = false, defaultValue = "true") Boolean includeFoodItems);

    ResponseEntity<KitchenOrderListResponse> getKitchenOrderByStatus(@PathVariable String status,
                                                                     @RequestParam(name = "includeFoodItems", required = false, defaultValue = "false") Boolean includeFoodItems);

    ResponseEntity<KitchenOrderResponse> updateOrderStatusById(@PathVariable Integer id,
                                                               @PathVariable String newStatus,
                                                               @RequestParam Boolean forceUpdate,
                                                               @RequestParam(name = "updateCustomerOrder", required = false, defaultValue = "true") Boolean updateCustomerOrder);

}
