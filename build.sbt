import Dependencies._

lazy val root =
  (project in file("."))
    .enablePlugins(BuildInfoPlugin, JavaAppPackaging)
    .settings(
      name := "k8s-logging",
      organization := "com.ruchij",
      scalaVersion := SCALA_VERSION,
      libraryDependencies ++= rootDependencies ++ rootTestDependencies.map(_ % Test),
      buildInfoKeys := BuildInfoKey.ofN(name, organization, version, scalaVersion, sbtVersion),
      buildInfoPackage := "com.eed3si9n.ruchij",
      topLevelDirectory := None,
      testOptions in Test +=
        Tests.Argument(TestFrameworks.ScalaTest, "-h", "target/test-results")
    )

lazy val rootDependencies =
  Seq(akkaActor, faker, logback, scalaLogging, playJson, jodaTime)

lazy val rootTestDependencies =
  Seq(scalaTest, pegdown)

addCommandAlias("testWithCoverage", "; clean; coverage; test; coverageReport")
