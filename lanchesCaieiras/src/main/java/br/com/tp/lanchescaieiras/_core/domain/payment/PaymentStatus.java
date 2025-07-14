package br.com.tp.lanchescaieiras._core.domain.payment;


import br.com.tp.lanchescaieiras._core.commons.interfaces.EnumWithIdDescription;
import br.com.tp.lanchescaieiras._core.commons.utils.EnumUtils;
import br.com.tp.lanchescaieiras._core.domain.exceptions.PaymentException;

public enum PaymentStatus implements EnumWithIdDescription {
    CANCELLED(0, "Cancelled"),
    CHARGED(1, "Charged"),
    PAID(2, "Paid");

    private final int id;
    private final String description;

    PaymentStatus(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public static PaymentStatus fromId(Integer id) {
        return EnumUtils.fromId(PaymentStatus.class, id,
                new PaymentException("Id do status de pagamento inválido: " + id + ". Os ids válidos são: " + listOfAllowIds(), 400));
    }

    public static PaymentStatus fromDescription(String description) {
        return EnumUtils.fromDescription(PaymentStatus.class, description,
                new PaymentException("Status de pagamento inválido: " + description + ". Os status válidos são: " + listOfAllowDescriptions(), 400));
    }

    public static String listOfAllowDescriptions() {
        return EnumUtils.listOfAllowDescriptions(PaymentStatus.class);
    }

    public static String listOfAllowIds() {
        return EnumUtils.listOfAllowIds(PaymentStatus.class);
    }
}
