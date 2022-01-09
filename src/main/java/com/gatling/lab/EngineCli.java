package com.gatling.lab;

import com.gatling.lab.core.GatlingProperties;
import com.gatling.lab.core.GatlingRunner;
import com.gatling.lab.core.GatlingRunnerImpl;
import io.gatling.javaapi.core.Simulation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.StringUtils.isEmpty;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

@Slf4j
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Command(name = "gatling",
        description = "Gatling cli runner. Can be used to run multiple simulations",
        aliases = "gtl",
        mixinStandardHelpOptions = true)
public class EngineCli implements Callable<Integer> {

    @Option(names = { "--path", "-p" },
            description = "Simulations base package")
    private String simulationPath;

    @Option(names = { "--simul", "-s" },
            description = "Simulation name")
    private String simulation;

    @Option(names = { "--all", "-a" },
            description = "Run all simulation in package")
    private boolean runAllSimulation;

    @Option(names = { "--resultdir", "-rd" })
    private String resultDir;

    @Option(names = {"--ressourcedir", "-r"})
    private String ressourceDir;

    @Override
    public Integer call() throws Exception {

        this.checkArgs();

        Reflections reflections = new Reflections(this.simulationPath);
        Set<String> simulations = reflections.getSubTypesOf(Simulation.class)
                .stream()
                .map(Class::getName)
                .collect(Collectors.toSet());

        if (isNotEmpty(this.simulation)) {
            simulations = simulations.stream()
                    .filter(s -> s.contains(this.simulation))
                    .collect(Collectors.toSet());
        }
        log.info("Found simulations: {}", simulations.size());

        GatlingRunner runner = new GatlingRunnerImpl(GatlingProperties.builder()
                .simulationPath(this.simulationPath)
                .ressourceDir(this.ressourceDir)
                .resultDir(this.resultDir)
                .build());

        runner.runSimulations(simulations);

        return 1;
    }

    /**
     * Check gatling args
     * @throws URISyntaxException
     */
    private void checkArgs() throws URISyntaxException {
        Path path = Paths.get(EngineCli.class.getProtectionDomain().getCodeSource().getLocation().toURI());
        if (isEmpty(this.ressourceDir)) {
            this.ressourceDir = path.toString();
        }

        if (isEmpty(this.resultDir)) {
            this.resultDir = path.getParent() + "/results";
        }
    }
}
