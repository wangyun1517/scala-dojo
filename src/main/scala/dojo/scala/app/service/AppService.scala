package dojo.scala.app.service

import dojo.scala.app.model.{AppAction, GetRandomIntAction}

object AppService {

  def getRandomInt(min: Int, max: Int): AppAction[Int] = GetRandomIntAction(min, max, identity)
}
