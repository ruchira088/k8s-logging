package com.ruchij

import java.util.UUID

import akka.actor.ActorSystem
import com.eed3si9n.ruchij.BuildInfo
import com.ruchij.logging.models.{CustomLogger, RequestContext}
import com.ruchij.models.User
import com.ruchij.utils.Random

import scala.concurrent.duration._
import scala.concurrent.{ExecutionContext, ExecutionContextExecutor}
import scala.language.postfixOps

object App
{
  private val logger = CustomLogger[App.type]

  def main(args: Array[String]): Unit = {
    implicit val actorSystem: ActorSystem = ActorSystem(BuildInfo.name)
    implicit val executionContextExecutor: ExecutionContextExecutor = actorSystem.dispatcher

    actorSystem.scheduler.schedule(0 seconds, 1 second) {
      val user = Random[User]
      implicit val requestContext: RequestContext = RequestContext(UUID.randomUUID().toString, user.username, user.email)

      logger.info(s"${user.username} said: ${Random.quotes.generate()}")

      schedule(2 seconds) {
        logger.info(s"${user.username} said good-bye")
      }

      schedule(3 seconds) {
        logger.warn(s"${user.username} exited")
      }
    }

    logger.info("Application started.")(RequestContext("system-context", "system-user", "N/A"))
  }

  def schedule(finiteDuration: FiniteDuration)(action: => Unit)(implicit actorSystem: ActorSystem, executionContext: ExecutionContext): Unit =
    actorSystem.scheduler.scheduleOnce(finiteDuration)(action)
}
