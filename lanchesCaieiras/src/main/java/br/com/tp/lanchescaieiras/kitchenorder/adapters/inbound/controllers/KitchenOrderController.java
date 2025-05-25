package br.com.tp.lanchescaieiras.kitchenorder.adapters.inbound.controllers;

import br.com.tp.lanchescaieiras.kitchenorder.domain.KitchenOrder;
import br.com.tp.lanchescaieiras.kitchenorder.domain.KitchenOrderListResponse;
import br.com.tp.lanchescaieiras.kitchenorder.domain.KitchenOrderResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface KitchenOrderController {

    public ResponseEntity<KitchenOrderResponse> createKitchenOrder(@RequestBody KitchenOrder kitchenOrder);

    public ResponseEntity<KitchenOrderResponse> getKitchenOrderById(@PathVariable Integer id,
                                                                      @RequestParam(name = "includeFoodItems", required = false, defaultValue = "false") Boolean includeFoodItems);


    public ResponseEntity<KitchenOrderResponse> getKitchenOrderByCustomerOrderId(@PathVariable(name = "customerOrderId") Integer customerOrderId,
                                                                          @RequestParam(name = "includeFoodItems", required = false, defaultValue = "true") Boolean includeFoodItems);

    public ResponseEntity<KitchenOrderListResponse> getKitchenOrderByStatus(@PathVariable String status,
                                                                            @RequestParam(name = "includeFoodItems", required = false, defaultValue = "false") Boolean includeFoodItems);

    public ResponseEntity<KitchenOrderResponse> updateOrderStatusById(@PathVariable Integer id,
                                                                      @PathVariable String newStatus,
                                                                      @RequestParam Boolean forceUpdate,
                                                                      @RequestParam(name = "updateCustomerOrder", required = false, defaultValue = "true") Boolean updateCustomerOrder);

}
