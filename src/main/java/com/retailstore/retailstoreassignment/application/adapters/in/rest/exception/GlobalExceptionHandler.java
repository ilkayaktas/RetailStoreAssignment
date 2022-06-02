package com.retailstore.retailstoreassignment.application.adapters.in.rest.exception;

import com.retailstore.retailstoreassignment.config.AppLogger;
import com.retailstore.retailstoreassignment.domain.model.exception.DuplicateEmailFoundException;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Component;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.AbstractMap;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@Component
public class GlobalExceptionHandler {

  private static final Logger log = AppLogger.getLogger(GlobalExceptionHandler.class);

  @ExceptionHandler
  public ResponseEntity<AbstractMap.SimpleEntry<String, String>> handle(Exception exception) {
    log.error("Request could not be processed: ", exception);
    AbstractMap.SimpleEntry<String, String> response =
        new AbstractMap.SimpleEntry<>("message", "Request could not be processed!");
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
  }

  @ExceptionHandler(value = MethodArgumentNotValidException.class)
  public ResponseEntity<AbstractMap.SimpleEntry<String, List<String>>> handleException(
          HttpServletRequest request, MethodArgumentNotValidException e) {

    log.error("Global exception is handled: "+ e.getMessage()+" "+e);

    List<String> collect = e.getBindingResult()
            .getAllErrors().stream()
            .map(ObjectError::getDefaultMessage)
            .collect(Collectors.toList());

    AbstractMap.SimpleEntry<String, List<String>> response =
            new AbstractMap.SimpleEntry<>("message", collect);

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
  }

  @ExceptionHandler(value = ConstraintViolationException.class)
  public ResponseEntity<AbstractMap.SimpleEntry<String, List<String>>> handleConstraintViolationException(
          HttpServletRequest request, ConstraintViolationException e) {

    List<String> collect = e.getConstraintViolations().stream().map(ConstraintViolation::getMessage).toList();

    log.error("Global exception is handled: "+ e.getMessage());

    AbstractMap.SimpleEntry<String, List<String>> response =
            new AbstractMap.SimpleEntry<>("message", collect);

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);

  }

  @ExceptionHandler(value = HttpMessageNotReadableException.class)
  public ResponseEntity<AbstractMap.SimpleEntry<String, String>> handleHttpMessageNotReadableException(
          HttpServletRequest request, HttpMessageNotReadableException e) {

    log.error("Global exception is handled: "+ e.getMessage()+" "+e);

    AbstractMap.SimpleEntry<String, String> response =
            new AbstractMap.SimpleEntry<>("message", "Message body is missing or malformed. Check api documentation to proper body format.");
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
  }

  @ExceptionHandler(value = MissingServletRequestParameterException.class)
  public ResponseEntity<AbstractMap.SimpleEntry<String, String>> handleMissingServletRequestParameterException(
          HttpServletRequest request, MissingServletRequestParameterException e) {

    log.error("Global exception is handled: "+ e.getMessage()+" "+e);

    AbstractMap.SimpleEntry<String, String> response =
            new AbstractMap.SimpleEntry<>("message", e.getMessage());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
  }

  @ExceptionHandler(value = RuntimeException.class)
  public ResponseEntity<AbstractMap.SimpleEntry<String, String>> handleRuntimeException(
          HttpServletRequest request, RuntimeException e) {

    log.error("Global exception is handled: "+ e.getMessage()+" "+e);

    AbstractMap.SimpleEntry<String, String> response =
            new AbstractMap.SimpleEntry<>("message_", e.getMessage());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
  }

  @ExceptionHandler(value = NumberFormatException.class)
  public ResponseEntity<AbstractMap.SimpleEntry<String, String>> handleNumberFormatException(
          HttpServletRequest request, NumberFormatException e) {

    log.error("Global exception is handled: "+ e.getMessage()+" "+e);

    AbstractMap.SimpleEntry<String, String> response =
            new AbstractMap.SimpleEntry<>("message", e.getMessage());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
  }

  @ExceptionHandler(value =  InvalidCredentialException.class)
  public ResponseEntity<AbstractMap.SimpleEntry<String, String>> handleInvalidCredentialException(
          HttpServletRequest request, InvalidCredentialException e) {

    log.error("Global exception is handled: "+ e.getMessage()+" "+e);

    AbstractMap.SimpleEntry<String, String> response =
            new AbstractMap.SimpleEntry<>("message", e.getMessage());
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
  }

  @ExceptionHandler(value =  DuplicateEmailFoundException.class)
  public ResponseEntity<AbstractMap.SimpleEntry<String, String>> handleDuplicateEmailFoundException(
          HttpServletRequest request, DuplicateEmailFoundException e) {

    log.error("Global exception is handled: "+ e.getMessage()+" "+e);

    AbstractMap.SimpleEntry<String, String> response =
            new AbstractMap.SimpleEntry<>("message", e.getMessage());
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
  }

}
