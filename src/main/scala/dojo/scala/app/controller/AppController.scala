package dojo.scala.app.controller

import scala.util.Random

object AppController {

  def get(min: Int, max: Int): String = (min + Random.nextInt(max - min)).toString()

}
