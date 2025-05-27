package br.com.tp.lanchescaieiras.kitchenorder.adapters.inbound.controllers;

import br.com.tp.lanchescaieiras.kitchenorder.application.services.KitchenOrderServicesImpl;
import br.com.tp.lanchescaieiras.kitchenorder.domain.KitchenOrder;
import br.com.tp.lanchescaieiras.kitchenorder.domain.KitchenOrderListResponse;
import br.com.tp.lanchescaieiras.kitchenorder.domain.KitchenOrderResponse;
import br.com.tp.lanchescaieiras.kitchenorder.infraestructure.config.KitchenOrderConfig;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/kitchenOrders")
public class KitchenOrderControllerImpl implements KitchenOrderController {

    public final KitchenOrderServicesImpl kitchenOrderServices;

    public final KitchenOrderConfig kitchenOrderConfig;

    public KitchenOrderControllerImpl(KitchenOrderServicesImpl kitchenOrderServices, KitchenOrderConfig kitchenOrderConfig, KitchenOrderConfig kitchenOrderConfig1) {
        this.kitchenOrderServices = kitchenOrderServices;
        this.kitchenOrderConfig = kitchenOrderConfig1;
    }

    @Override
    @PostMapping()
    public ResponseEntity<KitchenOrderResponse> createKitchenOrder(@RequestBody KitchenOrder kitchenOrder) {
        KitchenOrder createdKitchenOrder = kitchenOrderServices.createKitchenOrder(kitchenOrder);
        return ResponseEntity.status(HttpStatus.CREATED)
                .header("Location", kitchenOrderConfig.getLocationPrefix() + "/" + createdKitchenOrder.getId())
                .body(new KitchenOrderResponse());
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<KitchenOrderResponse> getKitchenOrderById(@PathVariable Integer id,
                                                                    @RequestParam(name = "includeFoodItems", required = false, defaultValue = "true") Boolean includeFoodItems) {
        KitchenOrder kitchenOrder = kitchenOrderServices.findById(id, includeFoodItems);
        return new ResponseEntity<KitchenOrderResponse>(new KitchenOrderResponse(kitchenOrder), HttpStatus.OK);
    }


    @Override
    @GetMapping("/customerOrder/{customerOrderId}")
    public ResponseEntity<KitchenOrderResponse> getKitchenOrderByCustomerOrderId(@PathVariable(name = "customerOrderId") Integer customerOrderId,
                                                                                 @RequestParam(name = "includeFoodItems", required = false, defaultValue = "true") Boolean includeFoodItems) {
        return new ResponseEntity<KitchenOrderResponse>(new KitchenOrderResponse(kitchenOrderServices.getKitchenOrderByCustomerOrderById(customerOrderId, includeFoodItems)), HttpStatus.OK);

    }

    @Override
    @GetMapping("/status/{status}")
    public ResponseEntity<KitchenOrderListResponse>
    getKitchenOrderByStatus(@PathVariable(name = "status") String status,
                            @RequestParam(name = "includeFoodItems", required = false, defaultValue = "true") Boolean includeFoodItems) {
        List<KitchenOrder> kitchenOrders = kitchenOrderServices.findByStatus(status, includeFoodItems);
        if (kitchenOrders == null) {
            return new ResponseEntity<KitchenOrderListResponse>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<KitchenOrderListResponse>(new KitchenOrderListResponse(kitchenOrders), HttpStatus.OK);
    }

    @Override
    @PatchMapping("/{id}/updateStatus/{newStatus}")
    public ResponseEntity<KitchenOrderResponse> updateOrderStatusById(@PathVariable(name = "id", required = true) Integer id,
                                                                      @PathVariable(name = "newStatus", required = true) String newStatus,
                                                                      @RequestParam(name = "forceUpdate", required = false, defaultValue = "false") Boolean forceUpdate,
                                                                      @RequestParam(name = "updateCustomerOrder", required = false, defaultValue = "true") Boolean updateCustomerOrder) {
        KitchenOrder kitchenOrder = kitchenOrderServices.updateStatusById(id, newStatus, forceUpdate, updateCustomerOrder);

        return new ResponseEntity<KitchenOrderResponse>(new KitchenOrderResponse(kitchenOrder), HttpStatus.OK);

    }
}
