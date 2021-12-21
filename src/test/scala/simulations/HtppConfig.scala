package simulations

import io.gatling.http.Predef.http

import io.gatling.core.Predef._
import io.gatling.http.Predef._

object HtppConfig {
  val httpConf = http.baseUrl("https://api.spacexdata.com/latest/")
    .header("Accept", value="application/json")
    .header("content-type", value="application/json")
}
