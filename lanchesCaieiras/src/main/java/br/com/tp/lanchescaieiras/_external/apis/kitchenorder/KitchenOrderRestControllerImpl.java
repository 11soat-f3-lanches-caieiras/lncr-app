package br.com.tp.lanchescaieiras._external.apis.kitchenorder;

import br.com.tp.lanchescaieiras._core.adapters.kitchenorder.KitchenOrderControllerImpl;
import br.com.tp.lanchescaieiras._core.commons.dtos.kitchenorder.KitchenOrderDTO;
import br.com.tp.lanchescaieiras._core.commons.utils.ResponseEntityModelUtil;
import br.com.tp.lanchescaieiras._external.commons.model.ResponseListModel;
import br.com.tp.lanchescaieiras._external.commons.model.ResponseModel;
import br.com.tp.lanchescaieiras._external.configs.KitchenOrderConfig;
import br.com.tp.lanchescaieiras._external.dataproxy.KichenOrderDataProxy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/kitchenOrders")
public class KitchenOrderRestControllerImpl implements KitchenOrderRestController {

    public final KitchenOrderControllerImpl kichenOrderController;
    public final KichenOrderDataProxy kichenOrderDataProxy;
    public final KitchenOrderConfig kitchenOrderConfig;

    public KitchenOrderRestControllerImpl(KitchenOrderControllerImpl kichenOrderController, KichenOrderDataProxy kichenOrderDataProxy, KitchenOrderConfig kitchenOrderConfig) {
        this.kichenOrderController = new KitchenOrderControllerImpl(kichenOrderDataProxy);
        this.kichenOrderDataProxy = kichenOrderDataProxy;
        this.kitchenOrderConfig = kitchenOrderConfig;
    }

    @Override
    @PostMapping
    public ResponseEntity<ResponseModel<KitchenOrderDTO>> createKitchenOrder(@RequestBody KitchenOrderDTO kitchenOrderDTO) {
        kitchenOrderDTO = this.kichenOrderController.createKitchenOrder(kitchenOrderDTO);
        return ResponseEntityModelUtil.created(null, kitchenOrderConfig.getLocationPrefix() + "/" + kitchenOrderDTO.getId());
    }

    @Override
    @GetMapping("/{kitchenOrderId}")
    public ResponseEntity<ResponseModel<KitchenOrderDTO>> getKitchenOrderById(@PathVariable(name="kitchenOrderId") Integer kitchenOrderId,
                                                                              @RequestParam(name = "includeFoodItems", required = false, defaultValue = "false") Boolean includeFoodItems) {
        KitchenOrderDTO kitchenOrderDTO = this.kichenOrderController.getKitchenOrderById(kitchenOrderId, includeFoodItems);
        return ResponseEntityModelUtil.OK(kitchenOrderDTO);
    }

    @Override
    @GetMapping("/customerOrder/{customerOrderId}")
    public ResponseEntity<ResponseModel<KitchenOrderDTO>> getKitchenOrderByCustomerOrderId(@PathVariable(name = "customerOrderId") Integer customerOrderId,
                                                                                           @RequestParam(name = "includeFoodItems", required = false, defaultValue = "false") Boolean includeFoodItems) {
        KitchenOrderDTO kitchenOrderDTO = this.kichenOrderController.getKitchenOrderByCustomerOrderId(customerOrderId, includeFoodItems);
        return ResponseEntityModelUtil.OK(kitchenOrderDTO);
    }

    @Override
    @GetMapping("/status/{statusList}")
    public ResponseEntity<ResponseListModel<KitchenOrderDTO>> getKitchenOrderByStatusList(@PathVariable(name = "statusList") List<String> statusList,
                                                                                          @RequestParam(name = "includeFoodItems", required = false, defaultValue = "false") Boolean includeFoodItems) {
        List<KitchenOrderDTO> kitchenOrderDTO = this.kichenOrderController.getKitchenOrderByStatusList(statusList, includeFoodItems);
        return ResponseEntityModelUtil.listOK(kitchenOrderDTO);
    }

    @Override
    public ResponseEntity<ResponseModel<KitchenOrderDTO>> updateOrderStatusById(Integer kitchenOrderId, String newStatus, Boolean forceUpdate, Boolean updateCustomerOrder) {
        return null;
    }



    /*@Override
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

    }*/
}
