package br.com.tp.lanchescaieiras._external.integrations.customerorder;

public interface CustomerOrderIntegration {

    void updateCustomerOrderStatus(Integer customerOrderId, String newStatus);
}
