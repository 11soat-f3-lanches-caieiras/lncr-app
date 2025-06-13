package br.com.tp.lanchescaieiras.customer.domain.entities;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Customer {
    Integer id;
    String documentNumber;
    String name;
    String email;

    public Customer() {
    }

    public Customer(Integer id, String documentNumber, String name, String email) {
        this.id = id;
        this.documentNumber = documentNumber;
        this.name = name;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean documentNumberIsValid() {
        if (this.documentNumber.length() != 11 || documentNumber.matches("(\\d)\\1{10}")) {
            return false;
        }
        try {
            int sum1 = 0;
            int sum2 = 0;
            for (int i = 0; i < 9; i++) {
                int digit = Character.getNumericValue(documentNumber.charAt(i));
                sum1 += digit * (10 - i);
                sum2 += digit * (11 - i);
            }

            int checkDigit1 = (sum1 * 10) % 11;
            if (checkDigit1 == 10) checkDigit1 = 0;

            sum2 += checkDigit1 * 2;
            int checkDigit2 = (sum2 * 10) % 11;
            if (checkDigit2 == 10) checkDigit2 = 0;

            return checkDigit1 == Character.getNumericValue(documentNumber.charAt(9)) && checkDigit2 == Character.getNumericValue(documentNumber.charAt(10));
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean emailIsValid() {
        String emailRegex = "^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}(\\.[a-zA-Z]{2,})?$";
        return this.email != null && this.email.matches(emailRegex);
    }


    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", documentNumber='" + documentNumber + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}


