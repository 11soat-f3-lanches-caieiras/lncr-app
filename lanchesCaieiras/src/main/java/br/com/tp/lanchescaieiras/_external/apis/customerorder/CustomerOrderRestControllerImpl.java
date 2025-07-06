package br.com.tp.lanchescaieiras._external.apis.customerorder;

import br.com.tp.lanchescaieiras._core.commons.dtos.customerorder.CustomerOrderDTO;
import br.com.tp.lanchescaieiras._core.commons.interfaces.customerorder.CustomerOrderController;
import br.com.tp.lanchescaieiras._core.commons.utils.ResponseEntityUtil;
import br.com.tp.lanchescaieiras._external.commons.model.Response;
import br.com.tp.lanchescaieiras._external.configs.CustomerOrderConfig;
import br.com.tp.lanchescaieiras._external.dataproxy.CustomerOrderDataProxy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<Response<CustomerOrderDTO>> createCustomerOrder(@RequestBody CustomerOrderDTO customerOrderDTO) {
        customerOrderDTO = this.customerOrderController.create(customerOrderDataProxy, customerOrderDTO);
        return ResponseEntityUtil.created(null,customerOrderConfig.getLocationPrefix() + "/" + customerOrderDTO.getId());

        /*CustomerOrder createdCustomerOrder = customerOrderServices.createCustomerOrder(customerOrder);
        return ResponseEntity.status(HttpStatus.CREATED)
                .header("Location", customerOrderConfig.getLocationPrefix() + "/" + createdCustomerOrder.getId())
                .body(new CustomerOrderResponse());*/
    }

    /*@Override
    @GetMapping("/{id}")
    public ResponseEntity<CustomerOrderResponse> getCustomerOrderById(@PathVariable Integer id,
                                                                      @RequestParam(name = "includeFoodItems", required = false, defaultValue = "false") Boolean includeFoodItems) {
        CustomerOrder customerOrder = customerOrderServices.findById(id, includeFoodItems);
        return new ResponseEntity<CustomerOrderResponse>(new CustomerOrderResponse(customerOrder), HttpStatus.OK);
    }

    @Override
    @GetMapping("/status/{status}")
    public ResponseEntity<CustomerOrderListResponse>
    getCustomerOrderByStatus(@PathVariable(name = "status") String status,
                             @RequestParam(name = "includeFoodItems", required = false, defaultValue = "false") Boolean includeFoodItems) {
        List<CustomerOrder> customerOrders = customerOrderServices.findByStatus(status, includeFoodItems);
        if (customerOrders == null) {
            return new ResponseEntity<CustomerOrderListResponse>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<CustomerOrderListResponse>(new CustomerOrderListResponse(customerOrders), HttpStatus.OK);
    }

    @Override
    @PatchMapping("/{id}/updateStatus/{newStatus}")
    public ResponseEntity<CustomerOrderResponse> updateOrderStatusById(@PathVariable(name = "id", required = true) Integer id,
                                                                       @PathVariable(name = "newStatus", required = true) String newStatus,
                                                                       @RequestParam(name = "forceUpdate", required = false, defaultValue = "false") Boolean forceUpdate) {
        CustomerOrder customerOrder = customerOrderServices.updateStatusById(id, newStatus, forceUpdate);
        return new ResponseEntity<CustomerOrderResponse>(new CustomerOrderResponse(customerOrder), HttpStatus.OK);

    }*/
}
