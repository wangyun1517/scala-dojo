package dojo.scala.app.service

import cats.~>
import dojo.scala.app.model.{AppAction, GetRandomIntAction}

import scala.concurrent.{ExecutionContextExecutor, Future}
import scala.util.Random

class AppActionInterpreter(implicit ec: ExecutionContextExecutor) extends (AppAction ~> Future) {
  override def apply[A](action: AppAction[A]): Future[A] = action match {
    case GetRandomIntAction(min, max, onResult) => Future(onResult(min + Random.nextInt(max - min)))
  }
}
