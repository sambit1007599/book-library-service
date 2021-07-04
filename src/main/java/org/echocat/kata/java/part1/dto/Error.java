package org.echocat.kata.java.part1.dto;

import java.util.List;

import lombok.Data;

/**
 * The type Error.
 */
@Data
public class Error {

  /**
   * The Errors.
   */
  List<ErrorAttributes> errors;

}
