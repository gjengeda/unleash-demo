package no.bekk.fagdag20210303.unleashdemo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import no.finn.unleash.Unleash;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class Scheduler {

    private final Unleash unleash;

    @Scheduled(cron = "* * * * * ?")
    public void loggHvisToggleErPå() {
        if(unleash.isEnabled("unleash-demo.scheduler")) {
            log.info("Toggle er slått på");
        }
    }

}
