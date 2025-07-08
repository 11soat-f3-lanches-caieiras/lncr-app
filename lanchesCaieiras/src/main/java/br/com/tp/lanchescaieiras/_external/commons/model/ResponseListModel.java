package br.com.tp.lanchescaieiras._external.commons.model;

import br.com.tp.lanchescaieiras._core.commons.model.ResponseMetadata;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseListModel<T> {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final ResponseMetadata _response;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final List<T> _content;

    public ResponseListModel(ResponseMetadata response, List<T> _content) {
        this._response = response;
        this._content = _content;
    }

    public ResponseListModel(List<T> _content) {
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
