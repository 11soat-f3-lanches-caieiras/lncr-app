package br.com.tp.lncr.core.adapters.customer;

import br.com.tp.lncr.core.commons.dtos.customer.CustomerDTO;
import br.com.tp.lncr.core.domain.customer.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomerPresenterTest {
    private CustomerMapper mapper;
    private CustomerPresenter presenter;

    @BeforeEach
    void setUp() {
        mapper = new CustomerMapper();
        presenter = new CustomerPresenter(mapper);
    }

    @Test
    void testCreated() {
        Customer customer = new Customer(1, "123", "Tito", "tito@email.com");
        CustomerDTO dto = presenter.created(customer);
        assertEquals(customer.getId(), dto.getId());
    }

    @Test
    void testGetAll() {
        Customer customer = new Customer(1, "123", "Tito", "tito@email.com");
        List<CustomerDTO> dtos = presenter.getAll(List.of(customer));
        assertEquals(1, dtos.size());
    }

    @Test
    void testGetById() {
        Customer customer = new Customer(1, "123", "Tito", "tito@email.com");
        CustomerDTO dto = presenter.getbyId(customer);
        assertEquals(customer.getId(), dto.getId());
    }

    @Test
    void testGetByDocumentNumber() {
        Customer customer = new Customer(1, "123", "Tito", "tito@email.com");
        CustomerDTO dto = presenter.getByDocumentNumber(customer);
        assertEquals(customer.getDocumentNumber(), dto.getDocumentNumber());
    }

    @Test
    void testPartialUpdatedById() {
        Customer customer = new Customer(1, "123", "Tito", "tito@email.com");
        CustomerDTO dto = presenter.partialUpdatedById(customer);
        assertEquals(customer.getId(), dto.getId());
    }

    @Test
    void testGetByIdList() {
        Customer customer = new Customer(1, "123", "Tito", "tito@email.com");
        List<CustomerDTO> dtos = presenter.getByIdList(List.of(customer));
        assertEquals(1, dtos.size());
    }
}

