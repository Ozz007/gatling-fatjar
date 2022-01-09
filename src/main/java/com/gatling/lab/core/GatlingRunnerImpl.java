package com.gatling.lab.core;

import io.gatling.app.Gatling;
import io.gatling.core.config.GatlingPropertiesBuilder;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.time.Instant;
import java.util.Set;

@Slf4j
@AllArgsConstructor
public class GatlingRunnerImpl implements GatlingRunner {

    private GatlingProperties props;

    @Override
    public int runSimulations(Set<String> simulations) {
        if (simulations.isEmpty()) {
            log.warn("No Simulations Found");
            return 0;
        }

        simulations.forEach(simulation -> {
            Instant start = Instant.now();
            log.info("Start com.gatling.lab.simulation: {}", simulation);

            GatlingPropertiesBuilder builder = new GatlingPropertiesBuilder()
                    .simulationClass(simulation)
                    .resourcesDirectory(props.getRessourceDir())
                    .resultsDirectory(props.getResultDir());
            Gatling.fromMap(builder.build());

            log.info("End com.gatling.lab.simulation: {}, duration: {}", simulation,
                    Duration.between(start, Instant.now()));
        });

        return simulations.size();
    }
}
