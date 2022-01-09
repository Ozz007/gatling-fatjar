Gatling Java Fat Jar
============================================

Gatling Java fatjar executable demo.

Can launch one or multiple simulations.

**Getting started:**
```shell
user@MBP-user gatling-fatjar % java -jar target/gatling-fatjar-1.0.0-SNAPSHOT.jar --help
Usage: gatling [-ahV] -p=<simulationPath> [-r=<ressourceDir>] [-rd=<resultDir>]
[-s=<simulation>]
Gatling cli runner. Can be used to run multiple simulations
-a, --all                  Run all simulation in package
-h, --help                 Show this help message and exit.
-p, --path=<simulationPath>
Simulations base package
-r, --ressourcedir=<ressourceDir>
-rd, --resultdir=<resultDir>
-s, --simul=<simulation>   Simulation name
-V, --version              Print version information and exit.
```

**Run simulations:**
```shell
# Run all simulation from package
user@MBP-user gatling-fatjar % java -jar target/gatling-fatjar-1.0.0-SNAPSHOT.jar -p "com.gatling.lab.simulation"

# Run one simulation
user@MBP-user gatling-fatjar % java -jar target/gatling-fatjar-1.0.0-SNAPSHOT.jar -p "com.gatling.lab.simulation" -s "BasicSimulation1"
```