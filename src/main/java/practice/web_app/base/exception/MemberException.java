package practice.web_app.base.exception;

import practice.web_app.base.Code;

public class MemberException extends GeneralException{

    public MemberException(Code errorCode) {
        super(errorCode);
    }
}
