package com.ang.nav.exception;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Hidden
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ClienteNotFoundException.class)
    public ResponseEntity<?> handleClienteNotFound(
            ClienteNotFoundException ex,
            HttpServletRequest req
    ) {
        Map<String, Object> error = new HashMap<>();
        error.put("timestamp", LocalDateTime.now());
        error.put("status", 404);
        error.put("error", "NOT FOUND");
        error.put("message", ex.getMessage());
        error.put("path", req.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(TipoClienteNotFoundException.class)
    public ResponseEntity<?> handleTipoClienteNotFound(
            TipoClienteNotFoundException ex,
            HttpServletRequest req
    ) {
        Map<String, Object> error = new HashMap<>();
        error.put("timestamp", LocalDateTime.now());
        error.put("status", 404);
        error.put("error", "NOT FOUND");
        error.put("message", ex.getMessage());
        error.put("path", req.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGeneric(
            Exception ex,
            HttpServletRequest req
    ) {
        Map<String, Object> error = new HashMap<>();
        error.put("timestamp", LocalDateTime.now());
        error.put("status", 500);
        error.put("error", "INTERNAL SERVER ERROR");
        error.put("message", ex.getMessage());
        error.put("path", req.getRequestURI());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationException(MethodArgumentNotValidException ex,
                                                       HttpServletRequest req) {
        Map<String, Object> error = new HashMap<>();
        error.put("timestamp", LocalDateTime.now());
        error.put("status", 400);
        error.put("error", "BAD REQUEST");
        error.put("path", req.getRequestURI());

        Map<String, String> fields = new HashMap<>();

        ex.getBindingResult()
                .getFieldErrors()
                .forEach(fe -> {
            fields.put(fe.getField(), mapCustomMessage(fe));
        });

        error.put("fields", fields);

        return ResponseEntity.badRequest().body(error);
    }

    private String mapCustomMessage(FieldError fe) {

        return switch (fe.getField()) {
            case "celular" ->
                    "El celular debe tener formato internacional, ejemplo: +51987654321";

            case "nroDocumento" ->
                    "El número de documento debe contener solo números y tener entre 8, 20 dígitos";

            case "email" ->
                    "El correo electrónico no tiene un formato válido";

            default ->
                    fe.getDefaultMessage();
        };
    }

    @ExceptionHandler(NroDocumentoAlreadyExistsException.class)
    public ResponseEntity<?> handleDuplicateDocumento(
            NroDocumentoAlreadyExistsException ex,
            HttpServletRequest req
    ) {
        Map<String, Object> error = new HashMap<>();
        error.put("timestamp", LocalDateTime.now());
        error.put("status", 409);
        error.put("error", "CONFLICT");
        error.put("message", ex.getMessage());
        error.put("path", req.getRequestURI());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

}
