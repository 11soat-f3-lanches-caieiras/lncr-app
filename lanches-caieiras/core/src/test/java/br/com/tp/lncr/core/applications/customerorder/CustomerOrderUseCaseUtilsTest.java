package br.com.tp.lncr.core.applications.customerorder;

import br.com.tp.lncr.core.commons.exceptions.CustomerOrderException;
import br.com.tp.lncr.core.commons.interfaces.customerorder.CustomerOrderGateway;
import br.com.tp.lncr.core.domain.customerorder.CustomerOrder;
import br.com.tp.lncr.core.domain.customerorder.CustomerOrderCustomer;
import br.com.tp.lncr.core.domain.customerorder.CustomerOrderFoodItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class CustomerOrderUseCaseUtilsTest {
    private CustomerOrderGateway gateway;

    @BeforeEach
    void setUp() {
        gateway = mock(CustomerOrderGateway.class);
    }

    @Test
    void deveBuscarDetalhesDoClienteComSucesso() {
        CustomerOrderCustomer customer = new CustomerOrderCustomer(1, "Cliente");
        CustomerOrder order = new CustomerOrder();
        order.setCustomer(customer);
        when(gateway.getCustomerDetails(1)).thenReturn(customer);
        CustomerOrderUseCaseUtils.getCustomerDetails(order, gateway);
        assertEquals(1, order.getCustomer().getId());
    }

    @Test
    void deveLancarExcecaoQuandoClienteNaoEncontrado() {
        CustomerOrderCustomer customer = new CustomerOrderCustomer(1, "Cliente");
        CustomerOrder order = new CustomerOrder();
        order.setCustomer(customer);
        when(gateway.getCustomerDetails(1)).thenReturn(null);
        assertThrows(CustomerOrderException.class, () -> CustomerOrderUseCaseUtils.getCustomerDetails(order, gateway));
    }

    @Test
    void deveBuscarDetalhesDosItensComSucesso() {
        CustomerOrderFoodItem item = new CustomerOrderFoodItem(1, null, "", "", null, null);
        CustomerOrder order = new CustomerOrder();
        order.setFoodItems(Collections.singletonList(item));
        CustomerOrderFoodItem detailed = new CustomerOrderFoodItem(1, null, "Nome", "Desc", 10.0, null);
        when(gateway.getFoodItemsDetails(anyList())).thenReturn(Collections.singletonList(detailed));
        CustomerOrderUseCaseUtils.getFoodItemsDetails(order, gateway);
        assertEquals("Nome", order.getFoodItems().get(0).getName());
    }

    @Test
    void deveEnviarNotificacaoParaStatusKnown() {
        CustomerOrder order = mock(CustomerOrder.class);
        when(order.getId()).thenReturn(1);
        when(order.getStatus()).thenReturn("RECEIVED");
        doNothing().when(gateway).sendNotification(anyString(), anyInt(), anyString());
        CustomerOrderUseCaseUtils.sendNotification(order, gateway);
        verify(gateway).sendNotification(contains("RECEIVED"), eq(1), contains("Pagamento finalizado"));
    }

    @Test
    void naoDeveEnviarNotificacaoParaStatusDesconhecido() {
        CustomerOrder order = mock(CustomerOrder.class);
        when(order.getId()).thenReturn(1);
        when(order.getStatus()).thenReturn("UNKNOWN");
        CustomerOrderUseCaseUtils.sendNotification(order, gateway);
        verify(gateway, never()).sendNotification(anyString(), anyInt(), anyString());
    }
}
