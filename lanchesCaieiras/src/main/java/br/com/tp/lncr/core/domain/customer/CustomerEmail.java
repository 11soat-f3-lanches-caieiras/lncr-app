package br.com.tp.lncr.core.domain.customer;

public class CustomerEmail {
    private final String value;

    public CustomerEmail(String value) {
        if (!emailIsValid(value)) {
            throw new IllegalArgumentException("Email inválido: " + value);
        }
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public boolean emailIsValid(String value) {
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
