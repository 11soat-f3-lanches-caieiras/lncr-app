package br.com.tp.lanchescaieiras.customer.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseMetada {
    private String _traceId;
    private String _timestamp;
    private String _message;

    public ResponseMetada() {
    }
    public ResponseMetada(String _traceId, String _timestamp, String _message) {
        this._traceId = _traceId;
        this._timestamp = _timestamp;
        this._message = _message;
    }

    public ResponseMetada(String _traceId, String _timestamp) {
        this._traceId = _traceId;
        this._timestamp = _timestamp;
    }

    public String get_traceId() {
        return _traceId;
    }

    public void set_traceId(String _traceId) {
        this._traceId = _traceId;
    }

    public String get_timestamp() {
        return _timestamp;
    }

    public void set_timestamp(String _timestamp) {
        this._timestamp = _timestamp;
    }

    public String get_message() {
        return _message;
    }

    public void set_message(String _message) {
        this._message = _message;
    }
}
