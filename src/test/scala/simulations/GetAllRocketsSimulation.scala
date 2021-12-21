package simulations

import io.gatling.core.scenario.Simulation
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import simulations.HtppConfig.httpConf

class GetAllRocketsSimulation extends Simulation {
  def getAllRockets() = {
      exec(
        http("Get all rockets")
          .get("/rockets")
          .check(status is 200)
      )
  }

  val scn = scenario("Get all rockets scenario")
    .exec(getAllRockets())

  setUp(scn.inject(atOnceUsers(100))).protocols(httpConf)
}
