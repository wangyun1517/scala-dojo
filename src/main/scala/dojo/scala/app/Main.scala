package dojo.scala.app

import akka.http.scaladsl.model.{HttpRequest, HttpResponse}
import akka.http.scaladsl.server.Route
import dojo.scala.app.api.HttpClient
import dojo.scala.app.route.AppRoute
import dojo.scala.app.service.{AppActionInterpreter, RandomGenerator}

import scala.concurrent.Future
import scala.io.StdIn

object Main extends App with AkkaConfig with Servable with ServerConfig {
  private val randomGenerator = new RandomGenerator(new HttpClient)
  implicit val interpreter: AppActionInterpreter = new AppActionInterpreter(randomGenerator)

  val server = start()
  println(s"Server online at http://$interface:$port/")
  println("Press RETURN to stop...")
  StdIn.readLine()

  server.stop()

  def handler: (HttpRequest) => Future[HttpResponse] = Route.asyncHandler(new AppRoute[Future]().route)
}
