package dojo.scala.app

import akka.http.scaladsl.Http
import akka.http.scaladsl.Http.ServerBinding
import akka.http.scaladsl.model.{HttpRequest, HttpResponse}

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

trait Servable extends ServerConfig {

  this: AkkaConfig =>

  def handler: (HttpRequest) => Future[HttpResponse]

  def start() = new Server(Await.result(Http().bindAndHandleAsync(handler, interface, port),
    Duration.Inf))

  class Server(private val binding: ServerBinding) {
    def stop() = binding.unbind.onComplete(_ => actorSystem.terminate())
  }

}
