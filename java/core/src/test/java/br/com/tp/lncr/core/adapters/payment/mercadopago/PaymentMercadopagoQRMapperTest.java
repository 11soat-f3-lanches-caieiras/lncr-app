package br.com.tp.lncr.core.adapters.payment.mercadopago;

import br.com.tp.lncr.core.commons.dtos.payment.PaymentMercadopagoQrDTO;
import br.com.tp.lncr.core.domain.payment.PaymentMercadopagoQR;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class PaymentMercadopagoQRMapperTest {
    private final PaymentMercadopagoQRMapper mapper = new PaymentMercadopagoQRMapper();

    @Test
    void testPaymentMercadopagoQrToDTO_Null() {
        assertNull(mapper.paymentMercadopagoQrToDTO(null));
    }

    @Test
    void testPaymentMercadopagoQrToDomain_Null() {
        assertNull(mapper.paymentMercadopagoQrToDomain(null));
    }

    @Test
    void testPaymentMercadopagoQrToDTO_AndBack() {
        PaymentMercadopagoQR domain = new PaymentMercadopagoQR(1, 123, "Paid", 100.0, "EXT123", LocalDateTime.now(), LocalDateTime.now(), "MELI123", "qrdata");
        PaymentMercadopagoQrDTO dto = mapper.paymentMercadopagoQrToDTO(domain);
        assertNotNull(dto);
        PaymentMercadopagoQR domain2 = mapper.paymentMercadopagoQrToDomain(dto);
        assertNotNull(domain2);
    }
}
