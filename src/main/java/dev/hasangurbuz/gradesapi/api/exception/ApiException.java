package dev.hasangurbuz.gradesapi.api.exception;

import lombok.Getter;

@Getter
public class ApiException extends RuntimeException {
    private ApiExceptionCode code;
    private String message;

    public ApiException(ApiExceptionCode code, String message) {
        this.code = code;
        this.message = message;
    }
}
