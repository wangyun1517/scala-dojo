package dojo.scala.app.route

import akka.http.scaladsl.server.Directives._
import dojo.scala.app.controller.AppController

object AppRoute {

  val route = get {
    parameters("min".as[Int], "max".as[Int]) { (min, max) =>
      complete {
        AppController.get(min,max)
      }
    }
  }
}
