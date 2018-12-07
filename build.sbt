import sbt.Keys.libraryDependencies

lazy val scalaLoggingVersion = "3.9.0"
lazy val logbackClassicVersion = "1.2.3"
lazy val rxScalaVersion = "0.26.5"
lazy val akkaStreamVersion = "2.5.18"

lazy val commonSettings = Seq(
  organization := "com.stulsoft",
  version := "0.0.2",
  javacOptions ++= Seq("-source", "11"),
  scalaVersion := "2.12.7",
  scalacOptions ++= Seq(
    "-feature",
    "-language:implicitConversions",
    "-language:postfixOps"),
  libraryDependencies ++= Seq(
    "com.typesafe.scala-logging" %% "scala-logging" % scalaLoggingVersion,
    "ch.qos.logback" % "logback-classic" % logbackClassicVersion
  )
)

lazy val rxScala = (project in file("rxscala"))
  .settings(commonSettings: _*)
  .settings(
    libraryDependencies ++= Seq(
      "io.reactivex" %% "rxscala" % rxScalaVersion
    )
  )
  .settings(
    name := "rxScala"
  )

lazy val reactiveStream = (project in file("reactive-stream"))
  .settings(commonSettings: _*)
  .settings(
    name := "reactiveStream"
  )
  .settings(
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-stream" % akkaStreamVersion,
      "com.typesafe.akka" %% "akka-stream-testkit" % akkaStreamVersion % "test"
    )
  )
