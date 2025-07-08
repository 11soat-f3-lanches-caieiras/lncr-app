package br.com.tp.lanchescaieiras._external.apis.customerorder;

import br.com.tp.lanchescaieiras._core.commons.dtos.customerorder.CustomerOrderDTO;
import br.com.tp.lanchescaieiras._core.commons.interfaces.customerorder.CustomerOrderController;
import br.com.tp.lanchescaieiras._core.commons.utils.ResponseEntityModelUtil;
import br.com.tp.lanchescaieiras._external.commons.model.ResponseListModel;
import br.com.tp.lanchescaieiras._external.commons.model.ResponseModel;
import br.com.tp.lanchescaieiras._external.configs.CustomerOrderConfig;
import br.com.tp.lanchescaieiras._external.dataproxy.CustomerOrderDataProxy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customerOrders")
public class CustomerOrderRestControllerImpl implements CustomerOrderRestController {

    public final CustomerOrderConfig customerOrderConfig;
    public final CustomerOrderDataProxy customerOrderDataProxy;
    public final CustomerOrderController customerOrderController;

    public CustomerOrderRestControllerImpl(CustomerOrderConfig customerOrderConfig, CustomerOrderDataProxy customerOrderDataProxy, CustomerOrderController customerOrderController) {
        this.customerOrderConfig = customerOrderConfig;
        this.customerOrderDataProxy = customerOrderDataProxy;
        this.customerOrderController = customerOrderController;
    }

    @Override
    @PostMapping()
    public ResponseEntity<ResponseModel<CustomerOrderDTO>> createCustomerOrder(@RequestBody CustomerOrderDTO customerOrderDTO) {
        customerOrderDTO = this.customerOrderController.create(customerOrderDataProxy, customerOrderDTO);
        return ResponseEntityModelUtil.created(customerOrderDTO,customerOrderConfig.getLocationPrefix() + "/" + customerOrderDTO.getId());

    }


    @Override
    @GetMapping("/{customerOrderId}")
    public ResponseEntity<ResponseModel<CustomerOrderDTO>> getCustomerOrderById(@PathVariable(name="customerOrderId") Integer customerOrderId,
                                                                                @RequestParam(name = "includeFoodItems", required = false, defaultValue = "false") Boolean includeFoodItems) {
        CustomerOrderDTO customerOrderDTO = this.customerOrderController.getById(customerOrderDataProxy, customerOrderId, includeFoodItems);
        return ResponseEntityModelUtil.OK(customerOrderDTO);
    }

    @Override
    @GetMapping("/status/{statusList}")
    public ResponseEntity<ResponseListModel<CustomerOrderDTO>>
    getCustomerOrderByStatus(@PathVariable(name = "statusList") List<String> statusList,
                             @RequestParam(name = "includeFoodItems", required = false, defaultValue = "false") Boolean includeFoodItems) {
        List<CustomerOrderDTO> customerOrderDTOList = this.customerOrderController.getByStatusList(customerOrderDataProxy, statusList, includeFoodItems);
        return ResponseEntityModelUtil.listOK(customerOrderDTOList);
    }
    /*
    @Override
    @PatchMapping("/{id}/updateStatus/{newStatus}")
    public ResponseEntity<CustomerOrderResponse> updateOrderStatusById(@PathVariable(name = "id", required = true) Integer id,
                                                                       @PathVariable(name = "newStatus", required = true) String newStatus,
                                                                       @RequestParam(name = "forceUpdate", required = false, defaultValue = "false") Boolean forceUpdate) {
        CustomerOrder customerOrder = customerOrderServices.updateStatusById(id, newStatus, forceUpdate);
        return new ResponseEntity<CustomerOrderResponse>(new CustomerOrderResponse(customerOrder), HttpStatus.OK);

    }*/
}
