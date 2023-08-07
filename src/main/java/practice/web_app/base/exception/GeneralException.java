package practice.web_app.base.exception;

import practice.web_app.base.Code;

public class GeneralException extends RuntimeException{

    private final Code errorCode;

    public GeneralException(String message) {
        super(Code.INTERNAL_ERROR.getMessage(message));
        this.errorCode = Code.INTERNAL_ERROR;
    }
}
