package com.gatling.lab.simulation.ui;

import com.gatling.lab.protocol.HttpProtocol;
import com.gatling.lab.scenario.BasicScenario;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;

import static io.gatling.javaapi.core.CoreDsl.atOnceUsers;

public class BasicSimulation3 extends Simulation {
    ScenarioBuilder scn1 = BasicScenario.scenario1();

    {
        setUp(scn1.injectOpen(atOnceUsers(1)).protocols(HttpProtocol.httpProtocol));
    }
}
