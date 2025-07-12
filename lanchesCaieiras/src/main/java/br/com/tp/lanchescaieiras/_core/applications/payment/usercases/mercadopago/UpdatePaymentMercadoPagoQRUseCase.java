package br.com.tp.lanchescaieiras._core.applications.payment.usercases.mercadopago;

import br.com.tp.lanchescaieiras._core.commons.interfaces.payment.PaymentGateway;
import br.com.tp.lanchescaieiras._core.domain.exceptions.PaymentException;
import br.com.tp.lanchescaieiras._core.domain.payment.PaymentMercadopagoQR;
import br.com.tp.lanchescaieiras._core.domain.payment.PaymentStatus;

public class UpdatePaymentMercadoPagoQRUseCase {

    private final PaymentGateway paymentGateway;

    public UpdatePaymentMercadoPagoQRUseCase(PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    public PaymentMercadopagoQR cancelByCustomerOrderId(Integer customerOrderId) {
        PaymentMercadopagoQR paymentMercadopagoQR = (PaymentMercadopagoQR) this.paymentGateway.getPaymentByCustomerOrderId(customerOrderId);
        if (paymentMercadopagoQR == null){
            throw new PaymentException("Não encontrado pagamento pelo id: "+ customerOrderId, 404);
        }
        if (paymentMercadopagoQR.getStatus().equals(PaymentStatus.CHARGED.getDescription())){
            paymentMercadopagoQR.setStatus(PaymentStatus.CANCELLED.getDescription());
            paymentMercadopagoQR = (PaymentMercadopagoQR) this.paymentGateway.save(paymentMercadopagoQR);
            return paymentMercadopagoQR;
        }
        else{
            throw new PaymentException("Não foi possível cancelar o pagamento do pedido: "+ customerOrderId+"Status atual do pagamento:" + paymentMercadopagoQR.getStatus(),400);
        }
    }

    public PaymentMercadopagoQR processPaymentReceived(String dataId) {
        Integer externalReferenceId = paymentGateway.getPaymentId(dataId);
        if (externalReferenceId !=null){
            PaymentMercadopagoQR paymentMercadopagoQR = (PaymentMercadopagoQR) this.paymentGateway.getPaymentByCustomerOrderId(externalReferenceId);
            if (paymentMercadopagoQR != null && paymentMercadopagoQR.getStatus().equals(PaymentStatus.CHARGED.getDescription())){
                paymentMercadopagoQR.setStatus(PaymentStatus.PAID.getDescription());
                paymentMercadopagoQR = (PaymentMercadopagoQR) this.paymentGateway.save(paymentMercadopagoQR);
                this.paymentGateway.updateCustomerOrderStatus(externalReferenceId,"Received");
                return paymentMercadopagoQR;
            }
        }
        throw new PaymentException("Não encontrado pedido no pagamento informado: " + dataId,404);
    }
}
