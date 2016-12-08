package dojo.scala.app.api

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{HttpRequest, HttpResponse}
import akka.stream.ActorMaterializer

import scala.concurrent.{ExecutionContextExecutor, Future}

class HttpClient(implicit ec: ExecutionContextExecutor, as: ActorSystem, mz: ActorMaterializer) extends (HttpRequest => Future[HttpResponse]) {
  override def apply(request: HttpRequest): Future[HttpResponse] = Http().singleRequest(request)
}
