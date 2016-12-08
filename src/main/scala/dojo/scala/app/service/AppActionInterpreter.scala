package dojo.scala.app.service

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import cats.~>
import dojo.scala.app.model.{AppAction, GetRandomIntAction}

import scala.concurrent.{ExecutionContextExecutor, Future}

class AppActionInterpreter(client: RandomGenerator)(implicit ec: ExecutionContextExecutor) extends (AppAction ~> Future) {
  override def apply[A](action: AppAction[A]): Future[A] = action match {
    case GetRandomIntAction(min, max, onResult) => client.getRandomInt(min, max) map onResult
  }

}
