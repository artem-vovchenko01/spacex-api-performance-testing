package simulations
import io.gatling.core.scenario.Simulation
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import simulations.HtppConfig.httpConf

class GetRocketByIdSimulation extends Simulation {
  def getRocketInfo(id: String) = {
    exec(
      http("Get rocket info by id")
        .get(s"/rockets/$id")
        .check(status is 200)
    )
  }

  val rocketId = "5e9d0d95eda69973a809d1ec"

  val scn = scenario("Get rocket info by id scenario")
    .exec(getRocketInfo(rocketId))

  setUp(scn.inject(atOnceUsers(100))).protocols(httpConf)
}
