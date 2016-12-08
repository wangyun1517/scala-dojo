package dojo.scala.app.service

import akka.actor.ActorSystem
import akka.http.scaladsl.model.HttpRequest
import akka.stream.ActorMaterializer
import akka.util.ByteString
import dojo.scala.app.api.HttpClient

import scala.concurrent.ExecutionContextExecutor

class RandomGenerator(httpClient: HttpClient)(implicit ec: ExecutionContextExecutor, as: ActorSystem, mz: ActorMaterializer)  {
  def getRandomInt(min: Int, max: Int) =

    for {
      resp <- httpClient(HttpRequest(uri = s"https://www.random.org/integers/?num=1&min=$min&max=$max&col=5&base=10&format=plain&rnd=new"))
      byte <- resp.entity.dataBytes.runFold(ByteString(""))(_ ++ _)
      body = byte.utf8String.trim.toInt
    } yield body


}
