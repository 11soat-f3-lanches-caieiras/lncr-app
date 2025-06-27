package br.com.tp.lanchescaieiras.customer.adapters;

import br.com.tp.lanchescaieiras.commons.domain.Response;
import br.com.tp.lanchescaieiras.commons.domain.ResponseList;
import br.com.tp.lanchescaieiras.customer.external.config.CustomerConfig;
import br.com.tp.lanchescaieiras.commons.dtos.CustomerDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;


public class CustomerPresenter {

    public CustomerPresenter() {
    }

    public ResponseEntity<Response<CustomerDTO>> created(CustomerDTO customerDTO, CustomerConfig customerConfig) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .header("Location", customerConfig.getLocationPrefix() + "/" + customerDTO.getId())
                .body(new Response<>(null));
    }

    public ResponseEntity<Response<CustomerDTO>> getById(CustomerDTO customerDTO) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new Response<>(customerDTO));
    }

    public ResponseEntity<Response<CustomerDTO>> getByDocumentNumber(CustomerDTO customerDTO) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new Response<>(customerDTO));
    }

    public ResponseEntity<ResponseList<CustomerDTO>> getAll(List<CustomerDTO> customerDTO) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseList<>(customerDTO));
    }

    public ResponseEntity<Response<CustomerDTO>> deleted(CustomerDTO customerDTO) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new Response<>(null));
    }
}
