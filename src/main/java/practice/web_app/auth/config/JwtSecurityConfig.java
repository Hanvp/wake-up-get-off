package practice.web_app.auth.config;

import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import practice.web_app.auth.filter.JwtRequestFilter;
import practice.web_app.auth.handler.JwtAuthenticationExceptionHandler;
import practice.web_app.auth.provider.TokenProvider;

@RequiredArgsConstructor
public class JwtSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final TokenProvider tokenProvider;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        JwtRequestFilter jwtFilter = new JwtRequestFilter(tokenProvider);
        JwtAuthenticationExceptionHandler jwtAuthenticationExceptionHandler = new JwtAuthenticationExceptionHandler();
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(jwtAuthenticationExceptionHandler, JwtRequestFilter.class);
    }
}
