package br.com.tp.lanchescaieiras.customerorder.adapters.outbound.integrations;

public interface PaymentIntegration {

    void createPayment(String payment);
}
