package br.com.tp.lanchescaieiras._external.dataproxy;

import br.com.tp.lanchescaieiras._core.commons.dtos.payment.PaymentMercadopagoQrDTO;
import br.com.tp.lanchescaieiras._core.commons.interfaces.payment.PaymentDatabase;
import br.com.tp.lanchescaieiras._external.datasources.postgres.payment.mercadopago.JpaMercadoPagoQrRepositoryImpl;
import br.com.tp.lanchescaieiras._external.integrations.customerorder.CustomerOrderIntegration;
import br.com.tp.lanchescaieiras._external.integrations.notifcation.NotificationIntegraionImpl;
import br.com.tp.lanchescaieiras._external.integrations.payment.mercadopago.MercadoPagoIntegrationImpl;

import java.util.List;

public class PaymentMercadoPagoQrDataProxy implements PaymentDatabase<PaymentMercadopagoQrDTO> {

    private final JpaMercadoPagoQrRepositoryImpl jpaMercadoPagoQrPostgresDatabase;
    private final MercadoPagoIntegrationImpl mercadoPagoIntegration;
    private final CustomerOrderIntegration customerOrderIntegration;
    private final NotificationIntegraionImpl notificationIntegration;

    public PaymentMercadoPagoQrDataProxy(JpaMercadoPagoQrRepositoryImpl jpaMercadoPagoQrPostgresDatabase, MercadoPagoIntegrationImpl mercadoPagoIntegration, CustomerOrderIntegration customerOrderIntegration, NotificationIntegraionImpl notificationIntegration) {
        this.jpaMercadoPagoQrPostgresDatabase = jpaMercadoPagoQrPostgresDatabase;
        this.mercadoPagoIntegration = mercadoPagoIntegration;
        this.customerOrderIntegration = customerOrderIntegration;
        this.notificationIntegration = notificationIntegration;
    }

    @Override
    public PaymentMercadopagoQrDTO createPaymentCharge(PaymentMercadopagoQrDTO paymentDTO) {
        paymentDTO = this.jpaMercadoPagoQrPostgresDatabase.save(paymentDTO);
        paymentDTO = this.mercadoPagoIntegration.createOrder(paymentDTO);
        paymentDTO = this.jpaMercadoPagoQrPostgresDatabase.save(paymentDTO);
        return paymentDTO;

    }

    @Override
    public PaymentMercadopagoQrDTO findPaymentById(Integer paymentId) {
        return this.jpaMercadoPagoQrPostgresDatabase.findById(paymentId);
    }

    @Override
    public PaymentMercadopagoQrDTO findPaymentByCustomerOrderId(Integer customerOrderId) {
        return this.jpaMercadoPagoQrPostgresDatabase.findByCustomerOrderId(customerOrderId);
    }

    @Override
    public PaymentMercadopagoQrDTO save(PaymentMercadopagoQrDTO paymentDTO) {
        return this.jpaMercadoPagoQrPostgresDatabase.save(paymentDTO);
    }

    @Override
    public void updateCustomerOrderStatus(Integer customerOrderId, String newStatus) {
        this.customerOrderIntegration.updateCustomerOrderStatus(customerOrderId,newStatus);
    }

    @Override
    public List<PaymentMercadopagoQrDTO> findByStatusList(List<Integer> paymentStatusIdList) {
        return this.jpaMercadoPagoQrPostgresDatabase.findByStatusList(paymentStatusIdList);
    }

    @Override
    public void sendNotification(String notificationType, Integer artefactId, String message) {
        this.notificationIntegration.sendNotification(notificationType, artefactId, message);
    }

    @Override
    public void cancelPaymentOrder(String meliId) {
        this.mercadoPagoIntegration.cancelOrder(meliId);
    }

    @Override
    public void refundPaymentOrder(String meliId) {
        this.mercadoPagoIntegration.refundOrder(meliId);
    }
}
