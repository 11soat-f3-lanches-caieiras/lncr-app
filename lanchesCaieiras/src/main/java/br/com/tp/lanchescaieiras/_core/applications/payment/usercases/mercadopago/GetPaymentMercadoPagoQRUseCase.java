package br.com.tp.lanchescaieiras._core.applications.payment.usercases.mercadopago;

import br.com.tp.lanchescaieiras._core.commons.interfaces.payment.PaymentGateway;
import br.com.tp.lanchescaieiras._core.domain.exceptions.PaymentException;
import br.com.tp.lanchescaieiras._core.domain.payment.PaymentMercadopagoQR;
import br.com.tp.lanchescaieiras._core.domain.payment.PaymentStatus;

import java.util.ArrayList;
import java.util.List;

public class GetPaymentMercadoPagoQRUseCase {

    private final PaymentGateway paymentGateway;

    public GetPaymentMercadoPagoQRUseCase(PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    public PaymentMercadopagoQR getById(Integer paymentId) {
        PaymentMercadopagoQR paymentMercadopagoQR = (PaymentMercadopagoQR) this.paymentGateway.getPaymentById(paymentId);
        if (paymentMercadopagoQR == null){
            throw new PaymentException("Não encontrado pagamento pelo id: "+ paymentId, 404);
        }
        return paymentMercadopagoQR;
    }

    public PaymentMercadopagoQR getByCustomerOrderId(Integer customerOrderId) {
        PaymentMercadopagoQR paymentMercadopagoQR = (PaymentMercadopagoQR) this.paymentGateway.getPaymentByCustomerOrderId(customerOrderId);
        if (paymentMercadopagoQR == null){
            throw new PaymentException("Não encontrado pagamento pelo id: "+ customerOrderId, 404);
        }
        return paymentMercadopagoQR;
    }

    public List<PaymentMercadopagoQR> getByStatusList(List<String> paymentStatusList) {
        List<Integer> statusIdsList = getStatusListIds(paymentStatusList);
        List<PaymentMercadopagoQR> paymentMercadopagoQRList = this.paymentGateway.getPaymentMercadoPagoQRList(statusIdsList);
        if (paymentMercadopagoQRList == null || paymentStatusList.isEmpty()){
            throw new PaymentException("Não encontradas ordens com os status:" + paymentStatusList,404);
        }
        return paymentMercadopagoQRList;
    }


    private List<Integer> getStatusListIds(List<String> statusList){
        List<Integer> statusListIds = new ArrayList<>();
        for(String status : statusList){
            statusListIds.add(PaymentStatus.fromDescription(status).getId());
        }
        return statusListIds;
    }
}
