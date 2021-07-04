package org.echocat.kata.java.part1.error.exception;

import lombok.Getter;

/**
 * The type Csv file not found exception.
 */
@Getter
public class CsvFileNotFoundException extends RuntimeException {

  /**
   * Instantiates a new Csv file not found exception.
   *
   * @param message the message
   */
  public CsvFileNotFoundException(String message) {
    super(message);
  }

}
