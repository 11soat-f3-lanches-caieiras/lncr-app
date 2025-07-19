package br.com.tp.lncr.core.commons.interfaces.customerorder;

import br.com.tp.lncr.core.commons.dtos.kitchenorder.KitchenOrderDTO;
import br.com.tp.lncr.core.commons.dtos.payment.PaymentMercadopagoQrDTO;
import br.com.tp.lncr.core.domain.customerorder.CustomerOrder;
import br.com.tp.lncr.core.domain.customerorder.CustomerOrderCustomer;
import br.com.tp.lncr.core.domain.customerorder.CustomerOrderFoodItem;

import java.util.List;

public interface CustomerOrderGateway {

    CustomerOrder createCustomerOrder(CustomerOrder customerOrder);

    CustomerOrderCustomer getCustomerDetails(Integer customerIdList);

    List<CustomerOrderCustomer> getCustomerDetailsList(List<Integer> customerIdList);

    List<CustomerOrderFoodItem> getFoodItemsDetails(List<Integer> foodItemListIds);

    void createPaymentCharge(CustomerOrder customerOrder);

    void sendNotification(String notificationType, Integer artefactId, String message);

    CustomerOrder getCustomerOrderById(Integer customerOrderId);

    CustomerOrder getCustomerOrderById(Integer customerOrderId, Boolean includFoodItems);

    List<CustomerOrder> getCustomerOrderByStatusList(List<Integer> statusListIds, Boolean includeFoodItems);

    CustomerOrder updateCustomerOrder(CustomerOrder updateCustomerOrder);

    void createKitchenOrder(CustomerOrder updateCustomerOrder);

    void cancelPaymentChargeByCustomerOrderId(Integer customerOrderId);

    PaymentMercadopagoQrDTO getPaymentByCustomerOrderId(Integer id);

    void cancelKitchenOrderById(Integer kitchenOrderOrderId);

    KitchenOrderDTO getKitchenOrderByCustomerOrderId(Integer id);
}
