package dojo.scala.app.model

sealed trait AppAction[A] {
  def map[B](f: A => B): AppAction[B] = this match {
    case GetRandomIntAction(min, max, onResult) =>
      GetRandomIntAction(min, max, onResult andThen f)
  }

}

case class GetRandomIntAction[C](min: Int, max: Int, onResult: Int => C) extends AppAction[C]