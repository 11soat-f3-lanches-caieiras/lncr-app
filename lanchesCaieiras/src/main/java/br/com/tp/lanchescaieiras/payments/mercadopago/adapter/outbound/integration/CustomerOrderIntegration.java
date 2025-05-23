package br.com.tp.lanchescaieiras.payments.mercadopago.adapter.outbound.integration;

public interface CustomerOrderIntegration {

    public void updateCustomerOrderStatus(Integer customerOrderId, String newStatus);
}
