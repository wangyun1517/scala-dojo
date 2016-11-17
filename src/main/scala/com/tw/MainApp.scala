package com.tw

import akka.http.scaladsl.model.{HttpRequest, HttpResponse}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route

import scala.concurrent.Future
import scala.io.StdIn

object MainApp extends App with Servable {
  override def handler: (HttpRequest) => Future[HttpResponse] = Route.asyncHandler {
    get {
      complete("Hello world")
    }
  }

  val server= start()
  StdIn.readLine()

  server.stop()
}
