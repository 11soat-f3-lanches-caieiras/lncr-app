package br.com.tp.lanchescaieiras.commons.domain;

public class Response <T> {
    private final ResponseMetadata _response;
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
