package no.gjengeda.unleashdemo.featuretoggles;

import lombok.RequiredArgsConstructor;
import no.finn.unleash.UnleashContext;
import no.finn.unleash.strategy.Strategy;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
public class BetauserStrategy implements Strategy {

    private static final Set<String> BETAUSERS = Set.of("bruker1", "bruker2");

    @Override
    public String getName() {
        return "betauser";
    }

    @Override
    public boolean isEnabled(Map<String, String> parameters) {
        return false;
    }

    @Override
    public boolean isEnabled(Map<String, String> parameters, UnleashContext unleashContext) {
        return unleashContext.getUserId()
                .map(currentUserId -> BETAUSERS.contains(currentUserId))
                .orElse(false);
    }

}
