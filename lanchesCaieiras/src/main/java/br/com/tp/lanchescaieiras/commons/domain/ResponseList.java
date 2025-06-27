package br.com.tp.lanchescaieiras.commons.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseList <T> {
    private final ResponseMetadata _response;
    private final List<T> _content;

    public ResponseList(ResponseMetadata response, List<T> _content) {
        this._response = response;
        this._content = _content;
    }

    public ResponseList(List<T> _content) {
        this._response = new ResponseMetadata();
        this._content = _content;
    }

    public ResponseMetadata get_response() {
        return _response;
    }

    public List<T> get_content() {
        return _content;
    }
}
