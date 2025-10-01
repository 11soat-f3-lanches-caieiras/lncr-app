package br.com.tp.lncr.app.datasources.postgres.customer;

import jakarta.persistence.*;


@Table(name = "customer",
        schema = "public",
        uniqueConstraints = {
                @UniqueConstraint(name = "customer_document_number_uk", columnNames = "documentNumber"),
                @UniqueConstraint(name = "customer_email_uk", columnNames = "email")
        },
        indexes = {
                @Index(name = "customer_id_idx", columnList = "id"),
                @Index(name = "customer_document_number_idx", columnList = "documentNumber"),
                @Index(name = "customer_email_idx", columnList = "email")
        })
@Entity
public class JpaCustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_id_seq")
    @SequenceGenerator(name = "customer_id_seq", sequenceName = "customer_id_seq", allocationSize = 1)
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
