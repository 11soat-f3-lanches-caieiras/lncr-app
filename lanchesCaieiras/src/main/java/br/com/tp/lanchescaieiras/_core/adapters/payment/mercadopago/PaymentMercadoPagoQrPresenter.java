package br.com.tp.lanchescaieiras._core.adapters.payment.mercadopago;

import br.com.tp.lanchescaieiras._core.commons.dtos.payment.PaymentMercadopagoQrDTO;
import br.com.tp.lanchescaieiras._core.commons.utils.StatusOrderUtils;
import br.com.tp.lanchescaieiras._core.domain.payment.PaymentMercadopagoQR;

import java.util.List;

public class PaymentMercadoPagoQrPresenter {

    private final PaymentMercadopagoQRMapper paymentMercadopagoQRMapper;

    public PaymentMercadoPagoQrPresenter(PaymentMercadopagoQRMapper paymentMercadopagoQRMapper) {
        this.paymentMercadopagoQRMapper = paymentMercadopagoQRMapper;
    }

    public PaymentMercadopagoQrDTO createdCharge(PaymentMercadopagoQR paymentMercadopagoQR) {
        return this.paymentMercadopagoQRMapper.paymentMercadopagoQrToDTO(paymentMercadopagoQR);
    }

    public PaymentMercadopagoQrDTO getById(PaymentMercadopagoQR paymentMercadopagoQR) {
        return this.paymentMercadopagoQRMapper.paymentMercadopagoQrToDTO(paymentMercadopagoQR);
    }

    public PaymentMercadopagoQrDTO getByCustomerOrderId(PaymentMercadopagoQR paymentMercadopagoQR) {
        return this.paymentMercadopagoQRMapper.paymentMercadopagoQrToDTO(paymentMercadopagoQR);
    }

    public PaymentMercadopagoQrDTO cancelByCustomerOrderId(PaymentMercadopagoQR paymentMercadopagoQR) {
        return this.paymentMercadopagoQRMapper.paymentMercadopagoQrToDTO(paymentMercadopagoQR);
    }

    public List<PaymentMercadopagoQrDTO> getByStatusList(List<PaymentMercadopagoQR> paymentMercadopagoQRList, List<String> paymentStatusList) {
        return StatusOrderUtils.sortByStatusOrder(paymentMercadopagoQRList,paymentStatusList)
                .stream()
                .map(paymentMercadopagoQRMapper::paymentMercadopagoQrToDTO)
                .toList();
    }
}
