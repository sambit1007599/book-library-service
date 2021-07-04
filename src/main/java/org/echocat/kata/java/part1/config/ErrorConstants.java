package org.echocat.kata.java.part1.config;

import org.echocat.kata.java.part1.dto.ErrorAttributes;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

/**
 * The type Error constants.
 */
@Component
@ConfigurationProperties("constants.error")
@Getter
@Setter
public class ErrorConstants {

  private ErrorAttributes fileNotFoundException;
  private ErrorAttributes genericException;
}
