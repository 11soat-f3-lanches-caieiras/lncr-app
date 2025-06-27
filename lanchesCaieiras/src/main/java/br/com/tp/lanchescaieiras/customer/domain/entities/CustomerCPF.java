package br.com.tp.lanchescaieiras.customer.domain.entities;

public class CustomerCPF implements IDocumentNumber {
    private final String value;

    public CustomerCPF(String value) {
        if (!documentNumberIsValid(value)) {
            throw new IllegalArgumentException("Número de documento inválido: " + value);
        }
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public final boolean documentNumberIsValid(String value) {
        if (value.length() != 11 ||value.matches("(\\d)\\1{10}")) {
            return false;
        }
        try {
            int sum1 = 0;
            int sum2 = 0;
            for (int i = 0; i < 9; i++) {
                int digit = Character.getNumericValue(value.charAt(i));
                sum1 += digit * (10 - i);
                sum2 += digit * (11 - i);
            }

            int checkDigit1 = (sum1 * 10) % 11;
            if (checkDigit1 == 10) checkDigit1 = 0;

            sum2 += checkDigit1 * 2;
            int checkDigit2 = (sum2 * 10) % 11;
            if (checkDigit2 == 10) checkDigit2 = 0;

            return checkDigit1 == Character.getNumericValue(value.charAt(9)) && checkDigit2 == Character.getNumericValue(value.charAt(10));
        } catch (NumberFormatException e) {
            return false;
        }

    }
}
