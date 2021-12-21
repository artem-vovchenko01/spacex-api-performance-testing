package simulations

import io.gatling.core.Predef.{atOnceUsers, exec, scenario}
import io.gatling.http.Predef.{http, status}
import simulations.HtppConfig.httpConf
import io.gatling.core.scenario.Simulation
import io.gatling.core.Predef._
import io.gatling.http.Predef._

class GetStarlinkSatellitesRampUsersSimulation extends Simulation {

  def getAllStarlinkSatellites() = {
    exec(
      http("Get all starlink satellites")
        .get("/starlink")
        .check(jsonPath("$[0].id").saveAs("satelliteId"))
        .check(status is 200)
    )
  }

  def getStarlinkSatelliteById() = {
    exec(http("Get starlink satellite by id")
      .get("/starlink/${satelliteId}")
      .check(status is 200))
  }

  val scn = scenario("Get all satellites and then get specific one by id")
    .exec(getAllStarlinkSatellites())
    .pause(2)
    .exec(getStarlinkSatelliteById())

  setUp(scn.inject(rampUsers(30) during 5)).protocols(httpConf)
}
