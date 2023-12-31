package practice.web_app.base;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import practice.web_app.base.exception.GeneralException;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Predicate;

@Getter
@RequiredArgsConstructor
public enum Code {
    OK(HttpStatus.OK,2000, "Ok"),

    OAUTH_LOGIN(HttpStatus.OK,2010, "로그인 입니다."),
    OAUTH_JOIN(HttpStatus.OK,2011,"회원가입 입니다."),

    // error Codes

    FORBIDDEN(HttpStatus.FORBIDDEN, 4003, "접근 권한이 없습니다."),
    BAD_REQUEST(HttpStatus.BAD_REQUEST,4005 ,"잘못된 요청 입니다."),

    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, 4006, "UnAuthorized"),
    JWT_BAD_REQUEST(HttpStatus.UNAUTHORIZED, 4006,"잘못된 JWT 서명입니다."),
    JWT_ACCESS_TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, 4007,"액세스 토큰이 만료되었습니다."),
    JWT_REFRESH_TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, 4008,"리프레시 토큰이 만료되었습니다. 다시 로그인하시기 바랍니다."),
    JWT_UNSUPPORTED_TOKEN(HttpStatus.UNAUTHORIZED, 4009,"지원하지 않는 JWT 토큰입니다."),
    JWT_TOKEN_NOT_FOUND(HttpStatus.UNAUTHORIZED, 4010,"유효한 JWT 토큰이 없습니다."),

    FEIGN_CLIENT_ERROR_400(HttpStatus.BAD_REQUEST, 4011, "feign에서 400번대 에러가 발생했습니다."),
    MEMBER_NOT_FOUND(HttpStatus.UNAUTHORIZED, 4013,"해당 사용자가 존재하지 않습니다"),

    INTERNAL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 5000, "Internal server Error"),
    FEIGN_CLIENT_ERROR_500(HttpStatus.INTERNAL_SERVER_ERROR, 5001, "Inter server Error in feign client");
    private final HttpStatus httpStatus;
    private final Integer code;
    private final String message;

    public String getMessage(Throwable e) {
        return this.getMessage(this.getMessage() + " - " + e.getMessage());
        // 결과 예시 - "Validation error - Reason why it isn't valid"
    }

    public String getMessage(String message) {
        return Optional.ofNullable(message)
                .filter(Predicate.not(String::isBlank))
                .orElse(this.getMessage());
    }

    public static Code valueOf(HttpStatus httpStatus) {
        if (httpStatus == null) {
            throw new GeneralException("HttpStatus is null.");
        }

        return Arrays.stream(values())
                .filter(errorCode -> errorCode.getHttpStatus() == httpStatus)
                .findFirst()
                .orElseGet(() -> {
                    if (httpStatus.is4xxClientError()) {
                        return Code.BAD_REQUEST;
                    } else if (httpStatus.is5xxServerError()) {
                        return Code.INTERNAL_ERROR;
                    } else {
                        return Code.OK;
                    }
                });
    }
}

