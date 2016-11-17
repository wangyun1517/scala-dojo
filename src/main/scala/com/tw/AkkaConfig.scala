package com.tw

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer

/**
  * Created by yunwang on 11/17/16.
  */
trait AkkaConfig {
  implicit lazy val actorSystem = ActorSystem()
  implicit lazy val materializer = ActorMaterializer()
  implicit lazy val executeContext = actorSystem.dispatcher
}
