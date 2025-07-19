package br.com.tp.lncr.core.applications.payment.usercases.mercadopago;

import br.com.tp.lncr.core.commons.enums.PaymentStatus;
import br.com.tp.lncr.core.commons.exceptions.PaymentException;
import br.com.tp.lncr.core.commons.interfaces.payment.PaymentGateway;
import br.com.tp.lncr.core.commons.utils.Logger;
import br.com.tp.lncr.core.domain.payment.PaymentMercadopagoQR;

import java.util.ArrayList;
import java.util.List;

public class GetPaymentMercadoPagoQRUseCase {

    private final PaymentGateway paymentGateway;

    public GetPaymentMercadoPagoQRUseCase(PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    public PaymentMercadopagoQR getById(Integer paymentId) {
        Logger.info("Iniciando busca de pagamento Mercado Pago QR pelo id: " + paymentId);
        PaymentMercadopagoQR paymentMercadopagoQR = (PaymentMercadopagoQR) this.paymentGateway.getPaymentById(paymentId);
        if (paymentMercadopagoQR == null){
            throw new PaymentException("Não encontrado pagamento pelo id: "+ paymentId, 404);
        }
        Logger.info("Pagamento Mercado Pago QR encontrado com sucesso, id: " + paymentId);
        return paymentMercadopagoQR;
    }

    public PaymentMercadopagoQR getByCustomerOrderId(Integer customerOrderId) {
        Logger.info("Iniciando busca de pagamento Mercado Pago QR pelo id do pedido do cliente: " + customerOrderId);
        PaymentMercadopagoQR paymentMercadopagoQR = (PaymentMercadopagoQR) this.paymentGateway.getPaymentByCustomerOrderId(customerOrderId);
        if (paymentMercadopagoQR == null){
            throw new PaymentException("Não encontrado pagamento pelo id: "+ customerOrderId, 404);
        }
        Logger.info("Pagamento do Mercado Pago QR pelo pedido do cliente id: "+ customerOrderId+ " encontrado");
        return paymentMercadopagoQR;
    }

    public List<PaymentMercadopagoQR> getByStatusList(List<String> paymentStatusList) {
        Logger.info("Buscando Pagamentos por Status: "+paymentStatusList);
        List<Integer> statusIdsList = getStatusListIds(paymentStatusList);
        List<PaymentMercadopagoQR> paymentMercadopagoQRList = this.paymentGateway.getPaymentMercadoPagoQRList(statusIdsList);
        if (paymentMercadopagoQRList == null || paymentStatusList.isEmpty()){
            throw new PaymentException("Não encontradas ordens com os status:" + paymentStatusList,404);
        }
        Logger.info("Encontrado pagamentos pelos status: "+paymentStatusList);
        return paymentMercadopagoQRList;
    }


    private List<Integer> getStatusListIds(List<String> statusList){
        Logger.debug("Convertendo Status por ids: "+ statusList);
        List<Integer> statusListIds = new ArrayList<>();
        for(String status : statusList){
            statusListIds.add(PaymentStatus.fromDescription(status).getId());
        }
        Logger.debug("Statu convertidos para os ids: "+statusListIds);
        return statusListIds;
    }
}
