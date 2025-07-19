package br.com.tp.lncr.core.adapters.payment.mercadopago;

import br.com.tp.lncr.core.commons.dtos.payment.PaymentMercadopagoQrDTO;
import br.com.tp.lncr.core.domain.payment.PaymentMercadopagoQR;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PaymentMercadoPagoQrPresenterTest {
    private final PaymentMercadopagoQRMapper mapper = mock(PaymentMercadopagoQRMapper.class);
    private final PaymentMercadoPagoQrPresenter presenter = new PaymentMercadoPagoQrPresenter(mapper);

    @Test
    void testCreatedCharge() {
        PaymentMercadopagoQR domain = mock(PaymentMercadopagoQR.class);
        PaymentMercadopagoQrDTO dto = mock(PaymentMercadopagoQrDTO.class);
        when(mapper.paymentMercadopagoQrToDTO(domain)).thenReturn(dto);
        assertEquals(dto, presenter.createdCharge(domain));
    }

    @Test
    void testGetById() {
        PaymentMercadopagoQR domain = mock(PaymentMercadopagoQR.class);
        PaymentMercadopagoQrDTO dto = mock(PaymentMercadopagoQrDTO.class);
        when(mapper.paymentMercadopagoQrToDTO(domain)).thenReturn(dto);
        assertEquals(dto, presenter.getById(domain));
    }

    @Test
    void testGetByCustomerOrderId() {
        PaymentMercadopagoQR domain = mock(PaymentMercadopagoQR.class);
        PaymentMercadopagoQrDTO dto = mock(PaymentMercadopagoQrDTO.class);
        when(mapper.paymentMercadopagoQrToDTO(domain)).thenReturn(dto);
        assertEquals(dto, presenter.getByCustomerOrderId(domain));
    }

    @Test
    void testCancelByCustomerOrderId() {
        PaymentMercadopagoQR domain = mock(PaymentMercadopagoQR.class);
        PaymentMercadopagoQrDTO dto = mock(PaymentMercadopagoQrDTO.class);
        when(mapper.paymentMercadopagoQrToDTO(domain)).thenReturn(dto);
        assertEquals(dto, presenter.cancelByCustomerOrderId(domain));
    }

    @Test
    void testGetByStatusList() {
        List<PaymentMercadopagoQR> domainList = List.of(mock(PaymentMercadopagoQR.class));
        List<String> statusList = List.of("PAID");
        when(mapper.paymentMercadopagoQrToDTO(any())).thenReturn(mock(PaymentMercadopagoQrDTO.class));
        List<PaymentMercadopagoQrDTO> result = presenter.getByStatusList(domainList, statusList);
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }
}


