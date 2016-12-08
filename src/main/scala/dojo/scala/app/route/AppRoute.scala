package dojo.scala.app.route

import akka.http.scaladsl.marshalling.ToResponseMarshaller
import akka.http.scaladsl.server.Directives._
import cats.~>
import dojo.scala.app.controller.AppController
import dojo.scala.app.model.AppAction


class AppRoute[A[_]](implicit interpreter: AppAction ~> A, marshaller: ToResponseMarshaller[A[String]]) {

  val route = get {
    parameters("min".as[Int], "max".as[Int]) { (min, max) =>
      complete {
        AppController.get(min, max).runAction[A]
      }
    }
  }
}
