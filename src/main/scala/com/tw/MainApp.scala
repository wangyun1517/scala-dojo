package com.tw

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{HttpRequest, HttpResponse}
import akka.stream.ActorMaterializer

import scala.concurrent.Future

//import scala.concurrent.ExecutionContext.Implicits._
object MainApp {

  def main(args: Array[String]): Unit = {
    implicit val actorSystem = ActorSystem()
    implicit val materializer = ActorMaterializer()
    implicit val executeContext = actorSystem.dispatcher
    val handler: (HttpRequest) => Future[HttpResponse] = ???
    Http().bindAndHandleAsync(handler, "localhost", 8080)
  }
}
