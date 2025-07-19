package br.com.tp.lncr.core.domain.customer;

import br.com.tp.lncr.core.commons.exceptions.CustomerException;

public class CustomerEmail {
    private final String value;

    public CustomerEmail(String value) {
        if (!emailIsValid(value)) {
            throw new CustomerException("Email inválido: " + value,400);
        }
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public boolean emailIsValid(String value) {
        if (value == null) throw new CustomerException("Email não informado. Informe um email válido", 400);
        String emailRegex = "^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}(\\.[a-zA-Z]{2,})?$";
        return value != null && value.matches(emailRegex);
    }

    @Override
    public String toString() {
        return "CustomerEmail{" +
                "value='" + value + '\'' +
                '}';
    }
}
