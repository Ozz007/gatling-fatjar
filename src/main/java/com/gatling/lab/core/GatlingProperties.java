package com.gatling.lab.core;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GatlingProperties {

    private String simulationPath;

    private String ressourceDir;

    private String resultDir;

}
