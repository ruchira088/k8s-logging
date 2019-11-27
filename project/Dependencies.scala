import sbt._

object Dependencies
{
  val SCALA_VERSION = "2.13.1"

  lazy val akkaActor = "com.typesafe.akka" %% "akka-actor" % "2.6.0"

  lazy val faker = "com.github.javafaker" % "javafaker" % "1.0.1"

  lazy val logback = "ch.qos.logback" % "logback-classic" % "1.2.3"
  
  lazy val scalaLogging = "com.typesafe.scala-logging" %% "scala-logging" % "3.9.2"

  lazy val playJson = "com.typesafe.play" %% "play-json" % "2.8.0"
  
  lazy val jodaTime =  "joda-time" % "joda-time" % "2.10.5"
  
  lazy val scalaTest = "org.scalatest" %% "scalatest" % "3.0.8"

  lazy val pegdown = "org.pegdown" % "pegdown" % "1.6.0"
}
