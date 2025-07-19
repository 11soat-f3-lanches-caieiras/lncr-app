package br.com.tp.lncr.core.commons.interfaces.customerorder;

import br.com.tp.lncr.core.commons.dtos.customerorder.CustomerOrderCustomerDTO;
import br.com.tp.lncr.core.commons.dtos.customerorder.CustomerOrderDTO;
import br.com.tp.lncr.core.commons.dtos.customerorder.CustomerOrderFoodItemDTO;
import br.com.tp.lncr.core.commons.dtos.kitchenorder.KitchenOrderDTO;
import br.com.tp.lncr.core.commons.dtos.payment.PaymentMercadopagoQrDTO;

import java.util.List;

public interface CustomerOrderDatabase {

    void cancelPaymentChargeByCustomerOrderId(Integer customerOrderId);

    void createKitchenOrder(CustomerOrderDTO customerOrderDTO);

    void createPaymentCharge(Integer customerOrderId, Double totalCost);

    CustomerOrderCustomerDTO findCustomerDetails(Integer customerId);

    List<CustomerOrderCustomerDTO> findCustomerDetailsList(List<Integer> customerIdList);

    List<CustomerOrderFoodItemDTO> findFoodItemsDetailsList(List<Integer> foodItemListIds);

    CustomerOrderDTO findCustomerOrderById(Integer customerOrderId, Boolean includFoodItems);

    List<CustomerOrderDTO> findCustomerOrderByStatusList(List<Integer> statusListIds, Boolean includeFoodItems);

    CustomerOrderDTO save(CustomerOrderDTO customerOrderDTO);

    void sendNotification(String notificationSource, Integer artefactId, String message);

    CustomerOrderDTO updateCustomerOrder(CustomerOrderDTO updatedCustomerOrderDTO);

    PaymentMercadopagoQrDTO findPaymentByCustomerOrderId(Integer id);

    KitchenOrderDTO findKitchenOrderByCustomerOrderId(Integer customerOrderId);

    void cancelKitchenOrderById(Integer kitchenOrderOrderId);
}
