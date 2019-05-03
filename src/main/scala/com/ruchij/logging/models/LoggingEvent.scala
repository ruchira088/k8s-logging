package com.ruchij.logging.models

import ch.qos.logback.classic.Level
import org.joda.time.DateTime
import play.api.libs.json.{Json, OWrites}
import com.ruchij.utils.Json._

case class LoggingEvent(
  timestamp: DateTime,
  level: Level,
  loggerName: String,
  requestId: String,
  username: Option[String],
  email: Option[String],
  message: String,
  threadName: String
)

object LoggingEvent {
  implicit val loggingEventWrites: OWrites[LoggingEvent] = Json.writes[LoggingEvent]
}
