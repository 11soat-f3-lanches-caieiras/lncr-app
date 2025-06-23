package br.com.tp.lanchescaieiras.customer.domain.entities;

public class Customer <DocumentNumber extends IDocumentNumber> {
    Integer id;
    DocumentNumber customerDocumentNumber;
    String name;
    CustomerEmail email;

    public Customer() {
    }

    public Customer(Integer id, DocumentNumber documentNumber, String name, String email) {
        this.id = id;
        this.customerDocumentNumber = documentNumber;
        this.name = name;
        this.email = new CustomerEmail(email);
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getDocumentNumber() { return customerDocumentNumber.getValue(); }

    public void setDocumentNumber(DocumentNumber documentNumber) { this.customerDocumentNumber = documentNumber; }

    public boolean documentNumberIsValid() { return this.customerDocumentNumber.documentNumberIsValid(); }

    public String getName() { return name; }

    public void setName(String name) { this.name = name;}

    public String getEmail() { return email.getValue(); }

    public void setEmail(String email) { this.email = new CustomerEmail(email); }

    public boolean emailIsValid() { return this.email.emailIsValid(); }

    @Override
    public String toString() {
        return "Customer{" + "id=" + id + ", documentNumber='" + customerDocumentNumber + '\'' + ", name='" + name + '\'' + ", email='" + email + '\'' + '}';
    }
}


