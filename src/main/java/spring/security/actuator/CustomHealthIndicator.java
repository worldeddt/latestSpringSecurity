package spring.security.actuator;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicReference;

@RestController
@RequestMapping("/health/modify")
@Slf4j
public class CustomHealthIndicator implements HealthIndicator {

    private final AtomicReference<Health> health = new AtomicReference<>(Health.up().build());

    @Override
    public Health health() {
        return health.get();
    }

    @PutMapping("/up")
    public Health up() {
        log.info("up :");
        Health up = Health.up().build();
        this.health.set(up);
        return up;
    }

    @PutMapping("/down")
    public Health down() {
        log.info("down :");
        Health down = Health.down().build();
        this.health.set(down);
        return down;
    }

    @PutMapping("/maintenance")
    public Health maintenance() {
        log.info("maintenance :");
        Health maintenance = Health.status(new Status("MAINTENANCE", "점검중")).build();
        this.health.set(maintenance);
        return maintenance;
    }
}
