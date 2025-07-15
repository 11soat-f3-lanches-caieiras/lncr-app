package br.com.tp.lanchescaieiras._external.datasources.postgres.customer;

import jakarta.persistence.*;


@Table(name = "customer",
        schema = "public",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "documentNumber"),
                @UniqueConstraint(columnNames = "email")
        })
@Entity
public class JpaCustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String documentNumber;
    String name;
    String email;

    public JpaCustomerEntity() {
    }

    public JpaCustomerEntity(Integer id, String documentNumber, String name, String email) {
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
}
