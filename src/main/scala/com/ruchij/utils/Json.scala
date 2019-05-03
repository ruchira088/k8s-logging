package com.ruchij.utils

import ch.qos.logback.classic.Level
import org.joda.time.DateTime
import play.api.libs.json.{JsString, Writes}

object Json {
  implicit val dateTimeWrites: Writes[DateTime] = (dateTime: DateTime) => JsString(dateTime.toString)

  implicit val levelWrites: Writes[Level] = (level: Level) => JsString(level.levelStr)
}
