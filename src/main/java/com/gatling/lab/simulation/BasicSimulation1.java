package com.gatling.lab.simulation;

import com.gatling.lab.protocol.HttpProtocol;
import com.gatling.lab.scenario.BasicScenario1;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;

import static io.gatling.javaapi.core.CoreDsl.atOnceUsers;

public class BasicSimulation1 extends Simulation {
    ScenarioBuilder scn1 = BasicScenario1.scenario2();

    {
        setUp(scn1.injectOpen(atOnceUsers(1)).protocols(HttpProtocol.httpProtocol));
    }
}
