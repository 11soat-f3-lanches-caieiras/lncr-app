package br.com.tp.lncr.external.integrations.customerorder;

public interface CustomerOrderIntegration {

    void updateCustomerOrderStatus(Integer customerOrderId, String newStatus);
}
