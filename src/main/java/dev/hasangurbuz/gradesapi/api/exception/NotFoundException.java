package dev.hasangurbuz.gradesapi.api.exception;

public class NotFoundException extends ApiException {
    public NotFoundException(String message) {
        super(ApiExceptionCode.NOT_FOUND, message);
    }
}
