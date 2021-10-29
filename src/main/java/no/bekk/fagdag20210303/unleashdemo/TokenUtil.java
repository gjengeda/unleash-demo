package no.bekk.fagdag20210303.unleashdemo;

import lombok.RequiredArgsConstructor;
import no.nav.security.token.support.core.context.TokenValidationContext;
import no.nav.security.token.support.core.context.TokenValidationContextHolder;
import no.nav.security.token.support.core.jwt.JwtTokenClaims;
import no.nav.security.token.support.spring.test.EnableMockOAuth2Server;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@EnableMockOAuth2Server
public class TokenUtil {

    private final TokenValidationContextHolder ctxHolder;

    public String getSubject() {
        return Optional.ofNullable(claimSet("iss-localhost"))
            .map(cs -> cs.getSubject())
            .orElse(null);
    }

    private JwtTokenClaims claimSet(String issuer) {
        return Optional.ofNullable(context())
                .map(s -> s.getClaims(issuer))
                .orElse(null);
    }

    private TokenValidationContext context() {
        return Optional.ofNullable(ctxHolder.getTokenValidationContext())
                .orElse(null);
    }

}
