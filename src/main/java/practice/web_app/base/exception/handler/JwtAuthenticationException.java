package practice.web_app.base.exception.handler;

import org.springframework.security.core.AuthenticationException;
import practice.web_app.base.Code;

public class JwtAuthenticationException extends AuthenticationException {

    public JwtAuthenticationException(Code code){ super(code.name());}
}
