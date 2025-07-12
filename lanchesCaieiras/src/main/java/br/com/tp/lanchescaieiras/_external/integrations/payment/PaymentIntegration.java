package br.com.tp.lanchescaieiras._external.integrations.payment;

public interface PaymentIntegration {

    void createPayment(Integer customerOrderId, Double totalCost);
}
