package br.com.tp.lanchescaieiras.commons.adapters.outbound.integrations;

public interface CustomerOrderIntegration {

    void updateCustomerOrderStatus(Integer customerOrderId, String newStatus);
}
