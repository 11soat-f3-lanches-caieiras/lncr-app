package br.com.tp.lanchescaieiras._core.applications.customerorder.usecases;

import br.com.tp.lanchescaieiras._core.commons.interfaces.customerorder.CustomerOrderGateway;

public class GetCustomerOrderUseCase {

    private final CustomerOrderGateway customerOrderGateway;

    public GetCustomerOrderUseCase(CustomerOrderGateway customerOrderGateway) {
        this.customerOrderGateway = customerOrderGateway;
    }
}
