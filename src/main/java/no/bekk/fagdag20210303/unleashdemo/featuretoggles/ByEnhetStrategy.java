package no.bekk.fagdag20210303.unleashdemo.featuretoggles;

import lombok.RequiredArgsConstructor;
import no.finn.unleash.UnleashContext;
import no.finn.unleash.strategy.Strategy;
import org.springframework.stereotype.Component;

import java.util.*;

import static java.util.stream.Collectors.toList;

@Component
@RequiredArgsConstructor
public class ByEnhetStrategy implements Strategy {

    private static final String PARAM = "valgtEnhet";

    private static final Map<String, List<String>> MAP = Map.of("bruker1", List.of("123", "456"), "bruker2", List.of("123", "789"));

    @Override
    public String getName() {
        return "byEnhet";
    }

    @Override
    public boolean isEnabled(Map<String, String> parameters) {
        return false;
    }

    @Override
    public boolean isEnabled(Map<String, String> parameters, UnleashContext unleashContext) {
        return unleashContext.getUserId()
                .flatMap(currentUserId -> Optional.ofNullable(parameters.get(PARAM))
                        .map(enheterString -> Set.of(enheterString.split(",\\s?")))
                        .map(enabledeEnheter -> !Collections.disjoint(enabledeEnheter, brukersEnheter(currentUserId))))
                .orElse(false);
    }

    private List<String> brukersEnheter(String currentUserId) {
        return MAP.getOrDefault(currentUserId, List.of());
    }

}
