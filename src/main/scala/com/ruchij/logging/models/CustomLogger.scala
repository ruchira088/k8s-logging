package com.ruchij.logging.models

import com.typesafe.scalalogging.Logger

import scala.reflect.ClassTag

class CustomLogger(logger: Logger) {
  def info(message: String)(implicit requestContext: RequestContext): Unit =
    logger.info(message, requestContext)

  def warn(message: String)(implicit requestContext: RequestContext): Unit =
    logger.warn(message, requestContext)

  def error(message: String)(implicit requestContext: RequestContext): Unit =
    logger.error(message, requestContext)
}

object CustomLogger {
  def apply[A: ClassTag]: CustomLogger = new CustomLogger(Logger[A])
}
