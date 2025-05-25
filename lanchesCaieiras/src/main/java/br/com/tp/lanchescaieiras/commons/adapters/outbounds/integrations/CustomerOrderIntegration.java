package br.com.tp.lanchescaieiras.commons.adapters.outbounds.integrations;

public interface CustomerOrderIntegration {

    public void updateCustomerOrderStatus(Integer customerOrderId, String newStatus);
}
