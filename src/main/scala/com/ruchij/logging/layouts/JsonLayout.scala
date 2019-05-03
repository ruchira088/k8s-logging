package com.ruchij.logging.layouts

import ch.qos.logback.classic.spi.ILoggingEvent
import ch.qos.logback.core.LayoutBase
import com.ruchij.logging.models.{LoggingEvent, RequestContext}
import org.joda.time.DateTime
import play.api.libs.json.Json

class JsonLayout extends LayoutBase[ILoggingEvent] {
  override def doLayout(event: ILoggingEvent): String = {
    val context = event.getArgumentArray.collectFirst { case requestContext: RequestContext => requestContext }

    Json.stringify {
      Json.toJson {
        LoggingEvent(
          new DateTime(event.getTimeStamp),
          event.getLevel,
          event.getLoggerName,
          context.map(_.requestId).getOrElse("missing request ID"),
          context.map(_.username),
          context.map(_.email),
          event.getFormattedMessage,
          event.getThreadName
        )
      }
    } + "\n"
  }
}
