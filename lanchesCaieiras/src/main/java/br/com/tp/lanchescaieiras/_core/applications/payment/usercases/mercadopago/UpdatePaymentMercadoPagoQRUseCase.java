package br.com.tp.lanchescaieiras._core.applications.payment.usercases.mercadopago;

import br.com.tp.lanchescaieiras._core.commons.enums.PaymentStatus;
import br.com.tp.lanchescaieiras._core.commons.exceptions.PaymentException;
import br.com.tp.lanchescaieiras._core.commons.interfaces.payment.PaymentGateway;
import br.com.tp.lanchescaieiras._core.domain.payment.PaymentMercadopagoQR;

import java.util.Map;

public class UpdatePaymentMercadoPagoQRUseCase {

    private final PaymentGateway paymentGateway;

    public UpdatePaymentMercadoPagoQRUseCase(PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    public PaymentMercadopagoQR cancelByCustomerOrderId(Integer customerOrderId) {
        PaymentMercadopagoQR paymentMercadopagoQR = (PaymentMercadopagoQR) this.paymentGateway.getPaymentByCustomerOrderId(customerOrderId);
        if (paymentMercadopagoQR == null) {
            throw new PaymentException("Não encontrado pagamento pelo id: " + customerOrderId, 404);
        }
        String previousStatus = paymentMercadopagoQR.getStatus();
        paymentMercadopagoQR.setStatus(PaymentStatus.CANCELLED.getDescription());
        paymentMercadopagoQR = (PaymentMercadopagoQR) this.paymentGateway.save(paymentMercadopagoQR);

        switch (previousStatus.toUpperCase()) {
            case "CHARGED":
                cancelPaymentCharged(paymentMercadopagoQR);
                break;
            case "PAID":
                cancelPaymentPaid(paymentMercadopagoQR);
                break;
        }
        return paymentMercadopagoQR;
    }

    public PaymentMercadopagoQR processPaymentReceived(String externalReference, String dataId, Map<String, Object> body) {
        Integer externalReferenceId = Integer.valueOf(externalReference);
        if (validadeOrderPayment(body)) {
            PaymentMercadopagoQR paymentMercadopagoQR = (PaymentMercadopagoQR) this.paymentGateway.getPaymentByCustomerOrderId(externalReferenceId);
            if (paymentMercadopagoQR != null && paymentMercadopagoQR.getMeliId().equals(dataId)) {
                if (paymentMercadopagoQR.getStatus().equals(PaymentStatus.CHARGED.getDescription())) {
                    paymentMercadopagoQR.setStatus(PaymentStatus.PAID.getDescription());
                    paymentMercadopagoQR = (PaymentMercadopagoQR) this.paymentGateway.save(paymentMercadopagoQR);
                    this.paymentGateway.updateCustomerOrderStatus(externalReferenceId, "Received");
                    this.paymentGateway.sendNotification("PAYMENT_MERCADOPAGO_QR_PAID", paymentMercadopagoQR.getOrderId(), "Pagamento com id: " + paymentMercadopagoQR.getOrderId() + " finalizado.");
                    return paymentMercadopagoQR;
                }
                throw new PaymentException("Status do pagamento inválido: " + paymentMercadopagoQR.getStatus(), 400);
            }
            throw new PaymentException("Não encontrado pedido com o id: " + externalReferenceId, 404);
        }
        throw new PaymentException("Status do pagamento inválido" + body.toString(), 400);
    }

    private void cancelPaymentCharged(PaymentMercadopagoQR paymentMercadopagoQR) {
        this.paymentGateway.cancelPaymentOrder(paymentMercadopagoQR.getMeliId());
        this.paymentGateway.sendNotification("PAYMENT_MERCADOPAGO_QR_CANCELLED", paymentMercadopagoQR.getOrderId(), "Cobrança criada com id: " + paymentMercadopagoQR.getOrderId() + " cancelada");
    }

    private void cancelPaymentPaid(PaymentMercadopagoQR paymentMercadopagoQR) {
        this.paymentGateway.refundPaymentOrder(paymentMercadopagoQR.getMeliId());
        this.paymentGateway.sendNotification("PAYMENT_MERCADOPAGO_QR_REFUND", paymentMercadopagoQR.getOrderId(), "Solicitado estorno para cobrança id: " + paymentMercadopagoQR.getOrderId());
    }

    private Boolean validadeOrderPayment(Map<String, Object> body) {
        if (body == null) return false;
        Object dataObj = body.get("data");
        if (dataObj instanceof Map) {
            Map<String, Object> data = (Map<String, Object>) dataObj;
            String status = (String) data.get("status");
            String statusDetail = (String) data.get("status_detail");
            return "processed".equals(status) && "accredited".equals(statusDetail);
        }
        return false;
    }


}
