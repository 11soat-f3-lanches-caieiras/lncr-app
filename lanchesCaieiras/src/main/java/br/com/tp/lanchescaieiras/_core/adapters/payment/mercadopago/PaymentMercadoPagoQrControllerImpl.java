package br.com.tp.lanchescaieiras._core.adapters.payment.mercadopago;

import br.com.tp.lanchescaieiras._core.applications.payment.usercases.mercadopago.CreatePaymentMercadoPagoQRUseCase;
import br.com.tp.lanchescaieiras._core.applications.payment.usercases.mercadopago.GetPaymentMercadoPagoQRUseCase;
import br.com.tp.lanchescaieiras._core.applications.payment.usercases.mercadopago.UpdatePaymentMercadoPagoQRUseCase;
import br.com.tp.lanchescaieiras._core.commons.dtos.payment.PaymentMercadopagoQRMapper;
import br.com.tp.lanchescaieiras._core.commons.dtos.payment.PaymentMercadopagoQrDTO;
import br.com.tp.lanchescaieiras._core.commons.interfaces.payment.PaymentController;
import br.com.tp.lanchescaieiras._core.commons.interfaces.payment.PaymentDatabase;
import br.com.tp.lanchescaieiras._core.commons.interfaces.payment.PaymentGateway;
import br.com.tp.lanchescaieiras._core.domain.payment.PaymentMercadopagoQR;

import java.util.List;

public class PaymentMercadoPagoQrControllerImpl implements PaymentController<PaymentMercadopagoQrDTO> {

    private final PaymentMercadopagoQRMapper paymentMercadopagoQRMapper;

    public PaymentMercadoPagoQrControllerImpl(PaymentMercadopagoQRMapper paymentMercadopagoQRMapper) {
        this.paymentMercadopagoQRMapper = paymentMercadopagoQRMapper;
    }

    @Override
    public PaymentMercadopagoQrDTO createPaymentCharge(PaymentDatabase paymentDatabase, PaymentMercadopagoQrDTO paymentDTO) {
        PaymentMercadopagoQR paymentMercadopagoQR = new CreatePaymentMercadoPagoQRUseCase(createPaymentMercadoPagoQrGateway(paymentDatabase)).createCharge(paymentDTO);
        return new PaymentMercadoPagoQrPresenter(paymentMercadopagoQRMapper).createdCharge(paymentMercadopagoQR);
    }

    @Override
    public PaymentMercadopagoQrDTO getPaymentById(PaymentDatabase paymentDatabase, Integer paymentId) {
        PaymentMercadopagoQR paymentMercadopagoQR = new GetPaymentMercadoPagoQRUseCase(createPaymentMercadoPagoQrGateway(paymentDatabase)).getById(paymentId);
        return new PaymentMercadoPagoQrPresenter(paymentMercadopagoQRMapper).getById(paymentMercadopagoQR);
    }

    @Override
    public PaymentMercadopagoQrDTO getPaymentByCustomerOrderId(PaymentDatabase paymentDatabase, Integer customerOrderId) {
        PaymentMercadopagoQR paymentMercadopagoQR = new GetPaymentMercadoPagoQRUseCase(createPaymentMercadoPagoQrGateway(paymentDatabase)).getByCustomerOrderId(customerOrderId);
        return new PaymentMercadoPagoQrPresenter(paymentMercadopagoQRMapper).getByCustomerOrderId(paymentMercadopagoQR);
    }

    @Override
    public PaymentMercadopagoQrDTO cancelPaymentByOrderId(PaymentDatabase paymentDatabase, Integer customerOrderId) {
        PaymentMercadopagoQR paymentMercadopagoQR = new UpdatePaymentMercadoPagoQRUseCase(createPaymentMercadoPagoQrGateway(paymentDatabase)).cancelByCustomerOrderId(customerOrderId);
        return new PaymentMercadoPagoQrPresenter(paymentMercadopagoQRMapper).cancelByCustomerOrderId(paymentMercadopagoQR);
    }

    @Override
    public PaymentMercadopagoQrDTO processPaymentReceived(PaymentDatabase paymentDatabase, String id) {
        PaymentMercadopagoQR paymentMercadopagoQR = new UpdatePaymentMercadoPagoQRUseCase(createPaymentMercadoPagoQrGateway(paymentDatabase)).processPaymentReceived(id);
        return new PaymentMercadoPagoQrPresenter(paymentMercadopagoQRMapper).cancelByCustomerOrderId(paymentMercadopagoQR);
    }

    @Override
    public List<PaymentMercadopagoQrDTO> getPaymentByStatusList(PaymentDatabase paymentDatabase, List<String> paymentStatusList) {
        List<PaymentMercadopagoQR> paymentMercadopagoQR = new GetPaymentMercadoPagoQRUseCase(createPaymentMercadoPagoQrGateway(paymentDatabase)).getByStatusList(paymentStatusList);
        return new PaymentMercadoPagoQrPresenter(paymentMercadopagoQRMapper).getByStatusList(paymentMercadopagoQR);
    }

    private PaymentGateway createPaymentMercadoPagoQrGateway(PaymentDatabase paymentDatabase){
        return new PaymentGatewayImpl(paymentDatabase, paymentMercadopagoQRMapper);
    }


}
