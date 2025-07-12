package br.com.tp.lanchescaieiras._external.dataproxy;

import br.com.tp.lanchescaieiras._core.commons.dtos.payment.PaymentMercadopagoQrDTO;
import br.com.tp.lanchescaieiras._core.commons.interfaces.payment.PaymentDatabase;
import br.com.tp.lanchescaieiras._external.datasources.postgres.payment.mercadopago.JpaMercadoPagoQrPostgresDatabaseImpl;
import br.com.tp.lanchescaieiras._external.integrations.customerorder.CustomerOrderIntegration;
import br.com.tp.lanchescaieiras._external.integrations.payment.mercadopago.MercadoPagoIntegrationImpl;

import java.util.List;

public class PaymentMercadoPagoQrDataProxy implements PaymentDatabase<PaymentMercadopagoQrDTO> {

    private final JpaMercadoPagoQrPostgresDatabaseImpl jpaMercadoPagoQrPostgresDatabase;
    private final MercadoPagoIntegrationImpl mercadoPagoIntegration;
    private final CustomerOrderIntegration customerOrderIntegration;

    public PaymentMercadoPagoQrDataProxy(JpaMercadoPagoQrPostgresDatabaseImpl jpaMercadoPagoQrPostgresDatabase, MercadoPagoIntegrationImpl mercadoPagoIntegration, CustomerOrderIntegration customerOrderIntegration) {
        this.jpaMercadoPagoQrPostgresDatabase = jpaMercadoPagoQrPostgresDatabase;
        this.mercadoPagoIntegration = mercadoPagoIntegration;
        this.customerOrderIntegration = customerOrderIntegration;
    }

    @Override
    public PaymentMercadopagoQrDTO createPaymentCharge(PaymentMercadopagoQrDTO paymentDTO) {
        paymentDTO = this.jpaMercadoPagoQrPostgresDatabase.save(paymentDTO);
        PaymentMercadopagoQrDTO newPaymentQrCode = this.mercadoPagoIntegration.createQRCode(paymentDTO);
        paymentDTO.setStoreId(newPaymentQrCode.getStoreId());
        paymentDTO.setQrData(newPaymentQrCode.getQrData());
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
    public Integer getPaymentId(String dataId) {
        return this.mercadoPagoIntegration.getPaymentId(dataId);
    }

    @Override
    public void updateCustomerOrderStatus(Integer customerOrderId, String newStatus) {
        this.customerOrderIntegration.updateCustomerOrderStatus(customerOrderId,newStatus);
    }

    @Override
    public List<PaymentMercadopagoQrDTO> findByStatusList(List<Integer> paymentStatusIdList) {
        return this.jpaMercadoPagoQrPostgresDatabase.findByStatusList(paymentStatusIdList);
    }
}
