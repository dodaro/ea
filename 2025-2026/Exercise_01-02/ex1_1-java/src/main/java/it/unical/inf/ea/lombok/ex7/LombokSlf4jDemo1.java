package it.unical.inf.ea.lombok.ex7;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LombokSlf4jDemo1 {

  public Double getSurcharge(Double transaction) {
    log.info("Surcharge Caliculation begins");
    log.debug("Surcharge amount " + transaction);
    if (Double.isNaN(transaction)) {
      log.error(transaction + " is not a valid amount");
      throw new RuntimeException("Invalid Transaction");
    }
    return Math.PI;
  }

  public static void main(String[] args) {
    new LombokSlf4jDemo1().getSurcharge(12.7);
  }
}