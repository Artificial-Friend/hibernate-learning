package foxtrot.uniform.charlie.kilo.exception;

import org.springframework.security.core.AuthenticationException;

public class JwtTokenException extends AuthenticationException {
    public JwtTokenException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public JwtTokenException(String msg) {
        super(msg);
    }
}