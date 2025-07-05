package br.com.tp.lanchescaieiras._external.datasources.postgres.customer;

import br.com.tp.lanchescaieiras._core.domain.customer.Customer;
import jakarta.persistence.*;


@Table(name = "customer",
        schema = "public",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "documentNumber"),
                @UniqueConstraint(columnNames = "email")
        })
@Entity
public class JpaCustomerPostgresEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String documentNumber;
    String name;
    String email;

    public JpaCustomerPostgresEntity() {
    }

    public JpaCustomerPostgresEntity(Integer id, String documentNumber, String name, String email) {
        this.id = id;
        this.documentNumber = documentNumber;
        this.name = name;
        this.email = email;
    }

    public JpaCustomerPostgresEntity(Customer customer) {
        this.id = customer.getId();
        this.name = customer.getName();
        this.documentNumber = customer.getDocumentNumber();
        this.email = customer.getEmail();
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
}
