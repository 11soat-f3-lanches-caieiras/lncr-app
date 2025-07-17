package br.com.tp.lanchescaieiras._core.adapters.payment.mercadopago;

import br.com.tp.lanchescaieiras._core.commons.dtos.payment.PaymentMercadopagoQrDTO;
import br.com.tp.lanchescaieiras._core.commons.interfaces.payment.PaymentDatabase;
import br.com.tp.lanchescaieiras._core.commons.interfaces.payment.PaymentGateway;
import br.com.tp.lanchescaieiras._core.domain.payment.PaymentMercadopagoQR;

import java.util.List;

public class PaymentGatewayImpl implements PaymentGateway<PaymentMercadopagoQR> {

    private final PaymentDatabase<PaymentMercadopagoQrDTO> paymentDatabase;
    private final PaymentMercadopagoQRMapper paymentMercadopagoQRMapper;

    public PaymentGatewayImpl(PaymentDatabase<PaymentMercadopagoQrDTO> paymentDatabase, PaymentMercadopagoQRMapper paymentMercadopagoQRMapper) {
        this.paymentDatabase = paymentDatabase;
        this.paymentMercadopagoQRMapper = paymentMercadopagoQRMapper;
    }

    @Override
    public PaymentMercadopagoQR createPaymentOrder(PaymentMercadopagoQR payment) {
        PaymentMercadopagoQrDTO paymentMercadopagoQrDTO = this.paymentMercadopagoQRMapper.paymentMercadopagoQrToDTO(payment);
        paymentMercadopagoQrDTO = this.paymentDatabase.createPaymentCharge(paymentMercadopagoQrDTO);
        return this.paymentMercadopagoQRMapper.paymentMercadopagoQrToDomain(paymentMercadopagoQrDTO);

    }

    @Override
    public PaymentMercadopagoQR savePayment(PaymentMercadopagoQR payment) {
        PaymentMercadopagoQrDTO paymentMercadopagoQrDTO = this.paymentMercadopagoQRMapper.paymentMercadopagoQrToDTO(payment);
        paymentMercadopagoQrDTO = this.paymentDatabase.save(paymentMercadopagoQrDTO);
        return this.paymentMercadopagoQRMapper.paymentMercadopagoQrToDomain(paymentMercadopagoQrDTO);
    }

    @Override
    public PaymentMercadopagoQR getPaymentById(Integer paymentId) {
        PaymentMercadopagoQrDTO paymentMercadopagoQrDTO = this.paymentDatabase.findPaymentById(paymentId);
        return this.paymentMercadopagoQRMapper.paymentMercadopagoQrToDomain(paymentMercadopagoQrDTO);
    }

    @Override
    public PaymentMercadopagoQR getPaymentByCustomerOrderId(Integer customerOrderId) {
        PaymentMercadopagoQrDTO paymentMercadopagoQrDTO = this.paymentDatabase.findPaymentByCustomerOrderId(customerOrderId);
        return this.paymentMercadopagoQRMapper.paymentMercadopagoQrToDomain(paymentMercadopagoQrDTO);
    }


    @Override
    public void updateCustomerOrderStatus(Integer customerOrderId, String newStatus) {
        this.paymentDatabase.updateCustomerOrderStatus(customerOrderId,newStatus);
    }

    @Override
    public List<PaymentMercadopagoQR> getPaymentMercadoPagoQRList(List<Integer> paymentStatusIdList) {
        List<PaymentMercadopagoQrDTO> mercadopagoQrDTOList = this.paymentDatabase.findByStatusList(paymentStatusIdList);
        return mercadopagoQrDTOList.stream().map(paymentMercadopagoQRMapper::paymentMercadopagoQrToDomain).toList();
    }

    @Override
    public void sendNotification(String notificationType, Integer artefactId, String message) {
        this.paymentDatabase.sendNotification(notificationType, artefactId, message);
    }

    @Override
    public void cancelPaymentOrderByProviderId(String meliId) {
        this.paymentDatabase.cancelPaymentOrder(meliId);
    }

    @Override
    public void refundPaymentOrderByProviderId(String meliId) {
        this.paymentDatabase.refundPaymentOrder(meliId);
    }
}
