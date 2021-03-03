package no.bekk.fagdag20210303.unleashdemo.featuretoggles;


import lombok.RequiredArgsConstructor;
import no.finn.unleash.Unleash;
import no.finn.unleash.UnleashContext;
import no.bekk.fagdag20210303.unleashdemo.TokenUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FeatureToggleService {

    private final Unleash unleash;
    private final TokenUtil tokenUtil;

    public Map<String, Boolean> hentFeatureToggles(List<String> features) {

        return features.stream().collect(Collectors.toMap(
                feature -> feature,
                this::isEnabled
        ));
    }

    private Boolean isEnabled(String feature) {
        return unleash.isEnabled(feature, contextMedInnloggetBruker());
    }

    private UnleashContext contextMedInnloggetBruker() {
        return UnleashContext.builder()
                .userId(tokenUtil.getSubject())
                .build();
    }
}