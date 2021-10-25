package no.bekk.fagdag20210303.unleashdemo.featuretoggles;

import lombok.RequiredArgsConstructor;
import no.finn.unleash.UnleashContext;
import no.finn.unleash.strategy.Strategy;
import org.springframework.stereotype.Component;

import java.util.*;

import static java.util.stream.Collectors.toList;

@Component
@RequiredArgsConstructor
public class ByDepartmentStrategy implements Strategy {

    private static final String PARAM = "department";

    private static final Map<String, List<String>> MAP = Map.of("user1", List.of("123", "456"), "user2", List.of("123", "789"));

    @Override
    public String getName() {
        return "byDepartment";
    }

    @Override
    public boolean isEnabled(Map<String, String> parameters) {
        return false;
    }

    @Override
    public boolean isEnabled(Map<String, String> parameters, UnleashContext unleashContext) {
        return unleashContext.getUserId()
                .flatMap(currentUserId -> Optional.ofNullable(parameters.get(PARAM))
                        .map(departmentsString -> Set.of(departmentsString.split(",\\s?")))
                        .map(enabledDepartments -> !Collections.disjoint(enabledDepartments, departmentsFor(currentUserId))))
                .orElse(false);
    }

    private List<String> departmentsFor(String currentUserId) {
        return MAP.getOrDefault(currentUserId, List.of());
    }

}
