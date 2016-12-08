package dojo.scala.app.route

import akka.http.scaladsl.server.Directives._
import cats.~>
import dojo.scala.app.controller.AppController
import dojo.scala.app.model.AppAction

import scala.concurrent.Future

class AppRoute(implicit interpreter: AppAction ~> Future) {

  val route = get {
    parameters("min".as[Int], "max".as[Int]) { (min, max) =>
      complete {
        AppController.get(min, max).runAction[Future]
      }
    }
  }
}
