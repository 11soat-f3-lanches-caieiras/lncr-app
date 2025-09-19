package br.com.tp.lncr.app.integrations.customerorder;

public interface CustomerOrderIntegration {

    void updateCustomerOrderStatus(Integer customerOrderId, String newStatus);
}
