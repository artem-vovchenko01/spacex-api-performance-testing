package simulations

import io.gatling.core.scenario.Simulation
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import simulations.HtppConfig.httpConf

class GetLatestLaunchSimulation extends Simulation {
  def getLatestLaunch() = {
    exec(
      http("Get latest launch")
        .get("/launches/latest")
        .check(status is 200)
    )
  }

  val scn = scenario("Get latest launch scenario")
    .exec(getLatestLaunch())

  setUp(scn.inject(atOnceUsers(100))).protocols(httpConf)
}
