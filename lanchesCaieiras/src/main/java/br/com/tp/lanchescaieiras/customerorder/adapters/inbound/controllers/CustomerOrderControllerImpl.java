package br.com.tp.lanchescaieiras.customerorder.adapters.inbound.controllers;

import br.com.tp.lanchescaieiras.customerorder.application.services.CustomerOrderServicesImpl;
import br.com.tp.lanchescaieiras.customerorder.domain.CustomerOrder;
import br.com.tp.lanchescaieiras.customerorder.domain.CustomerOrderListResponse;
import br.com.tp.lanchescaieiras.customerorder.domain.CustomerOrderResponse;
import br.com.tp.lanchescaieiras.customerorder.infraestructure.config.CustomerOrderConfig;
import br.com.tp.lanchescaieiras.customerorder.infraestructure.config.IntegrationConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customerOrders")
public class CustomerOrderControllerImpl implements CustomerOrderController {

    public final CustomerOrderServicesImpl customerOrderServices;
    public final CustomerOrderConfig customerOrderConfig;

    public CustomerOrderControllerImpl(CustomerOrderServicesImpl customerOrderServices, CustomerOrderConfig customerOrderConfig, CustomerOrderConfig customerOrderConfig1) {
        this.customerOrderServices = customerOrderServices;
        this.customerOrderConfig = customerOrderConfig1;
    }

    @Override
    @PostMapping()
    public ResponseEntity<CustomerOrderResponse> createCustomerOrder(@RequestBody CustomerOrder customerOrder) {
        CustomerOrder createdCustomerOrder = customerOrderServices.createCustomerOrder(customerOrder);
        return ResponseEntity.status(HttpStatus.CREATED)
                .header("Location", customerOrderConfig.getLocationPrefix() + "/" + createdCustomerOrder.getId())
                .body(new CustomerOrderResponse());
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<CustomerOrderResponse> getCustomerOrderById(@PathVariable Integer id,
                                                                      @RequestParam(name="includeFoodItems", required = false, defaultValue = "false") Boolean includeFoodItems) {
        CustomerOrder customerOrder = customerOrderServices.findById(id,includeFoodItems);
        return new ResponseEntity<CustomerOrderResponse> (new CustomerOrderResponse(customerOrder), HttpStatus.OK);
    }

    @Override
    @GetMapping("/status/{status}")
    public ResponseEntity<CustomerOrderListResponse>
            getCustomerOrderByStatus(@PathVariable(name = "status") String status,
                                     @RequestParam(name="includeFoodItems", required = false, defaultValue = "false") Boolean includeFoodItems) {
        return new ResponseEntity<CustomerOrderListResponse>(new CustomerOrderListResponse(customerOrderServices.findByStatus(status, includeFoodItems)), HttpStatus.OK);
    }
}
