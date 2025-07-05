package br.com.tp.lanchescaieiras._core.commons.utils;

import br.com.tp.lanchescaieiras._external.commons.model.Response;
import br.com.tp.lanchescaieiras._external.commons.model.ResponseList;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class ResponseEntityUtil {

    public static <T> ResponseEntity<Response<T>> response(T body, HttpStatus httpStatus, HttpHeaders httpHeaders) {
        return ResponseEntity.status(httpStatus)
                .headers(httpHeaders)
                .body(new Response<>(body));
    }

    public static <T> ResponseEntity<Response<T>> OK(T body) {
        return response(body, HttpStatus.OK, null);
    }

    public static <T> ResponseEntity<Response<T>> created(T body, String location) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", location);
        return ResponseEntityUtil.response(body, HttpStatus.CREATED, headers);
    }

    public static <T> ResponseEntity<ResponseList<T>> listOK(List<T> body) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseList<>(body));
    }
}
