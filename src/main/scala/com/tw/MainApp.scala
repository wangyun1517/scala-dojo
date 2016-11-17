package com.tw

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.Http.ServerBinding
import akka.http.scaladsl.model.{HttpRequest, HttpResponse}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.stream.ActorMaterializer

import scala.concurrent.Future
import scala.io.StdIn

//import scala.concurrent.ExecutionContext.Implicits._
object MainApp {

  def main(args: Array[String]): Unit = {
    implicit val actorSystem = ActorSystem()
    implicit val materializer = ActorMaterializer()
    implicit val executeContext = actorSystem.dispatcher
    val handler: (HttpRequest) => Future[HttpResponse] = Route.asyncHandler{
      get{
        complete("Hello world")
      }
    }
    val server: Future[ServerBinding] = Http().bindAndHandleAsync(handler, "localhost", 8080)
    println("start at localhost:8080")
    StdIn.readLine()

    server
      .flatMap(_.unbind).onComplete(_ => actorSystem.terminate() )
  }
}
