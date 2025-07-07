package br.com.tp.lanchescaieiras._external.commons.model;

import br.com.tp.lanchescaieiras._core.commons.model.ResponseMetadata;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final ResponseMetadata _response;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final T _content;

    public Response(ResponseMetadata response, T content) {
        this._response = response;
        this._content = content;
    }

    public Response(T content) {
        this._response = new ResponseMetadata();
        this._content = content;
    }

    public ResponseMetadata get_response() {
        return _response;
    }

    public T get_content() {
        return _content;
    }


}
