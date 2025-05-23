package br.com.tp.lanchescaieiras.payments.mercadopago.adapter.outbound.repositories;

import br.com.tp.lanchescaieiras.payments.mercadopago.adapter.outbound.entities.JpaPaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaPaymentRepository extends JpaRepository<JpaPaymentEntity, Integer> {

}
