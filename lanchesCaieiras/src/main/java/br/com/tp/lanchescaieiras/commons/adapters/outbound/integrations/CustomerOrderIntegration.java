package br.com.tp.lanchescaieiras.commons.adapters.outbound.integrations;

public interface CustomerOrderIntegration {

    public void updateCustomerOrderStatus(Integer customerOrderId, String newStatus);
}
