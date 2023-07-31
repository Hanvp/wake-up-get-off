package practice.web_app.auth.provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.security.Key;

@Component
public class TokenProvider implements InitializingBean {

    private final Logger LOGGER = LoggerFactory.getLogger(TokenProvider.class);

    private final String AUTHORITIES_KEY;

    public static final String AUTHORIZATION_HEADER ="Authorization";

    private final String secret;

    private final long accessTokenValidityInMilliseconds;

    private Key key;
}
