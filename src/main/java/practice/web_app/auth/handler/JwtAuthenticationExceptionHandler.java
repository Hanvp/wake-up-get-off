package practice.web_app.auth.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;
import practice.web_app.base.Code;
import practice.web_app.base.exception.common.ApiErrorResult;
import practice.web_app.base.exception.handler.JwtAuthenticationException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class JwtAuthenticationExceptionHandler extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try {
            filterChain.doFilter(request, response);
        } catch (JwtAuthenticationException authException) {
            response.setContentType("application/json; charset=UTF-8");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());

            PrintWriter writer = response.getWriter();
            String errorCodeName = authException.getMessage();
            Code code = Code.valueOf(errorCodeName);

            ApiErrorResult apiErrorResult = ApiErrorResult.builder()
                    .isSuccess(false)
                    .code(code.getCode())
                    .message(code.getMessage())
                    .result(null)
                    .build();

            writer.write(apiErrorResult.toString());
            writer.flush();
            writer.close();
        }
    }
}
