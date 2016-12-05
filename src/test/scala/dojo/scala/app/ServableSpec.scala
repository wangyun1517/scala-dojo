package dojo.scala.app

import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{HttpRequest, HttpResponse}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.util.ByteString
import com.tw.AkkaConfig
import org.scalacheck.Gen
import org.specs2.mutable.Specification

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

object ServableSpec extends Specification with AkkaConfig {
  "start" should {
    "response correct when send a request" in {
      val randomPort = Gen.chooseNum[Int](2000, 60000).sample.get
      val servable = new Servable {
        override val port = randomPort
        def handler: (HttpRequest) => Future[HttpResponse] = Route.asyncHandler {
          get {
            complete("Ok")
          }
        }
      }

      val server = servable.start()

      val body = Await.result(for {
        resp <- Http().singleRequest(HttpRequest(uri = s"http://localhost:$randomPort/test"))
        byte <- resp.entity.dataBytes.runFold(ByteString(""))(_ ++ _)
        body = byte.utf8String
      } yield body, Duration.Inf)

      server.stop()

      body === "Ok"
    }
  }
}
