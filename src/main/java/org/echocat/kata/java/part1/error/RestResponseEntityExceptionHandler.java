package org.echocat.kata.java.part1.error;

import java.util.List;

import org.echocat.kata.java.part1.config.ErrorConstants;
import org.echocat.kata.java.part1.dto.Error;
import org.echocat.kata.java.part1.dto.ErrorAttributes;
import org.echocat.kata.java.part1.error.exception.CsvFileNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * The type Rest response entity exception handler.
 */
@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

  private final ErrorConstants errorConstants;

  /**
   * Handle game over exception response entity.
   *
   * @param ex the ex
   * @return the response entity
   */
  @ExceptionHandler(CsvFileNotFoundException.class)
  public ResponseEntity<Error> handleGameOverException(CsvFileNotFoundException ex) {

    Error error =
        getErrorResponseEntity(errorConstants.getFileNotFoundException(), HttpStatus.NOT_FOUND, ex);

    log.error("{} :: {}", errorConstants.getFileNotFoundException().getCode(),
        errorConstants.getFileNotFoundException().getMessage());
    return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
  }

  /**
   * Handle game over exception response entity.
   *
   * @param ex the ex
   * @return the response entity
   */
  @ExceptionHandler(Exception.class)
  public ResponseEntity<Error> handleGameOverException(Exception ex) {

    Error error =
        getErrorResponseEntity(errorConstants.getGenericException(), HttpStatus.INTERNAL_SERVER_ERROR, ex);

    log.error("{} :: {}", errorConstants.getGenericException().getCode(),
        errorConstants.getGenericException().getMessage());
    return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  private Error getErrorResponseEntity(ErrorAttributes errorAttributes, HttpStatus httpStatus, Exception ex) {

    Error error = new Error();
    error.setErrors(
        List.of(ErrorAttributes.builder().code(errorAttributes.getCode()).message(errorAttributes.getMessage())
            .status(httpStatus.value()).params(List.of(ex.getMessage())).build()));

    return error;
  }
}
