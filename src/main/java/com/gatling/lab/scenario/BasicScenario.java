package com.gatling.lab.scenario;

import io.gatling.javaapi.core.ScenarioBuilder;

import java.time.Duration;

import static io.gatling.javaapi.core.CoreDsl.scenario;
import static io.gatling.javaapi.http.HttpDsl.http;

public class BasicScenario {

    public static ScenarioBuilder scenario1() {
        return scenario("Scenario Name") // A com.gatling.lab.scenario is a chain of requests and pauses
                .exec(http("request_1")
                        .get("/"))
                .pause(7) // Note that Gatling has recorded real time pauses
                .exec(http("request_2")
                        .get("/computers?f=macbook"))
                .pause(2)
                .exec(http("request_3")
                        .get("/computers/6"))
                .pause(3)
                .exec(http("request_4")
                        .get("/"))
                .pause(2)
                .exec(http("request_5")
                        .get("/computers?p=1"))
                .pause(Duration.ofMillis(670))
                .exec(http("request_6")
                        .get("/computers?p=2"))
                .pause(Duration.ofMillis(629))
                .exec(http("request_7")
                        .get("/computers?p=3"))
                .pause(Duration.ofMillis(734))
                .exec(http("request_8")
                        .get("/computers?p=4"))
                .pause(5)
                .exec(http("request_9")
                        .get("/computers/new"))
                .pause(1)
                .exec(http("request_10") // Here's an example of a POST request
                        .post("/computers")
                        .formParam("name", "Beautiful Computer") // Note the triple double quotes: used in Scala for protecting a whole chain of characters (no need for backslash)
                        .formParam("introduced", "2012-05-30")
                        .formParam("discontinued", "")
                        .formParam("company", "37"));
    }
}
