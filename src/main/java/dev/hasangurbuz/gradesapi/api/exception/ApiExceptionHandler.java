package dev.hasangurbuz.gradesapi.api.exception;

import org.openapitools.model.ErrorCodeDTO;
import org.openapitools.model.ErrorDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorDTO> handle(NotFoundException notFoundException) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setCode(ErrorCodeDTO.NOT_FOUND);
        errorDTO.setMessage(notFoundException.getMessage());
        return ResponseEntity.badRequest().body(errorDTO);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDTO> handle(MethodArgumentNotValidException e) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setCode(ErrorCodeDTO.INVALID_INPUT);
        errorDTO.setMessage(e.getBindingResult().getFieldError().getField() + " : is not valid");
        return ResponseEntity.badRequest().body(errorDTO);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorDTO> handle(HttpMessageNotReadableException e) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setCode(ErrorCodeDTO.INVALID_INPUT);
        errorDTO.setMessage("Request is not valid");
        return ResponseEntity.badRequest().body(errorDTO);
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorDTO> handle(ApiException apiException) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setCode(ErrorCodeDTO.SERVER_ERROR);
        errorDTO.setMessage("Please contact with adminstrator");
        return ResponseEntity.internalServerError().body(errorDTO);
    }

}
