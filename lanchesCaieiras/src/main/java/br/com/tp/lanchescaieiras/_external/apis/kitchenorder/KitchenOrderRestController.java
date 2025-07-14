package br.com.tp.lanchescaieiras._external.apis.kitchenorder;

import br.com.tp.lanchescaieiras._core.commons.dtos.kitchenorder.KitchenOrderDTO;
import br.com.tp.lanchescaieiras._external.commons.model.ResponseListModel;
import br.com.tp.lanchescaieiras._external.commons.model.ResponseModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface KitchenOrderRestController {

    ResponseEntity<ResponseModel<KitchenOrderDTO>> createKitchenOrder(@RequestBody KitchenOrderDTO kitchenOrderDTO);

    ResponseEntity<ResponseModel<KitchenOrderDTO>> getKitchenOrderById(@PathVariable(name="kitchenOrderId") Integer kitchenOrderId,
                                                             @RequestParam(name = "includeFoodItems", required = false, defaultValue = "false") Boolean includeFoodItems);


    ResponseEntity<ResponseModel<KitchenOrderDTO>> getKitchenOrderByCustomerOrderId(@PathVariable(name = "customerOrderId") Integer customerOrderId,
                                                                          @RequestParam(name = "includeFoodItems", required = false, defaultValue = "true") Boolean includeFoodItems);

    ResponseEntity<ResponseListModel<KitchenOrderDTO>> getKitchenOrderByStatusList(@PathVariable List<String> statusList,
                                                                               @RequestParam(name = "includeFoodItems", required = false, defaultValue = "false") Boolean includeFoodItems);

    ResponseEntity<ResponseModel<KitchenOrderDTO>> updateOrderStatusById(@PathVariable(name="kitchenOrderId") Integer kitchenOrderId,
                                                               @PathVariable(name="newStatus") String newStatus,
                                                               @RequestParam(name="forceUpdate",required = false,defaultValue = "false") Boolean forceUpdate,
                                                               @RequestParam(name = "updateCustomerOrder", required = false, defaultValue = "true") Boolean updateCustomerOrder);

}
