package br.com.tp.lanchescaieiras.commons.domain;

import java.util.List;

public class ResponseList <T> {
    private final ResponseMetadata _response;
    private final List<T> _content;

    public ResponseList(ResponseMetadata response, List<T> content) {
        this._response = response;
        this._content = content;
    }

    public ResponseList(List<T> content) {
        this._response = new ResponseMetadata();
        this._content = content;
    }

    public ResponseMetadata get_response() {
        return _response;
    }

    public List<T> get_content() {
        return _content;
    }
}
