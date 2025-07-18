package br.com.tp.lncr.external.integrations;

public class IntegrationException extends RuntimeException {
  private final Integer code;

  public IntegrationException(String message, Integer code) {
    super(message);
    this.code = code;
  }

  public Integer getCode() {
    return code;
  }

}
