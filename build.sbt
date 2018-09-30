name := "learning-tutorial"
version := "0.1"
scalaVersion := "2.12.7"
organization := "com.satyapal"
crossScalaVersions := Seq("2.11.12", "2.12.4")
resolvers += Resolver.sonatypeRepo("snapshots")

// -------------------------------------------------------------------------------------------------------------------
// Root Project
// -------------------------------------------------------------------------------------------------------------------
lazy val root = Project("learning-tutorial", file("."))
  .aggregate(akkaServices, webService)
  .dependsOn(akkaServices, webService)
  .settings(name := "learning-tutorial-platform")
  .settings(mainClass in (Compile, packageBin) := Some("com.satyapal.service.Application"))
  .settings(mainClass in (Compile, run) := Some("com.satyapal.service.Application"))

// -------------------------------------------------------------------------------------------------------------------
// Akka Service
// -------------------------------------------------------------------------------------------------------------------
lazy val akkaServices = Project("learning-service", file("modules/learning-service"))
  .settings(name := "learning-service")
  .settings(libraryDependencies ++= Seq(akkaActor, akkaTestKit, akkaSlf4j, scalaTest, jUnit))

// -------------------------------------------------------------------------------------------------------------------
// Web Service
// -------------------------------------------------------------------------------------------------------------------
lazy val webService = Project("web-service", file("modules/web-service"))
  .aggregate(akkaServices)
  .dependsOn(akkaServices)
  .settings(name := "web-service")
  .settings(libraryDependencies ++= Seq(guice, javaJdbc, jdbc, mySql, assertCore, awaitility, scalaTestPlus, jUnit))
  .enablePlugins(PlayJava, PlayEbean)

// Project Dependencies
val akkaVersion = "2.5.12"
val akkaActor = "com.typesafe.akka" %% "akka-actor" % akkaVersion
val akkaTestKit = "com.typesafe.akka" %% "akka-testkit" % akkaVersion
val akkaSlf4j = "com.typesafe.akka" %% "akka-slf4j" % akkaVersion
val scalaTest = "org.scalatest" %% "scalatest" % "3.0.5" % Test
val scalaTestPlus = "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % Test
val jUnit = "junit" % "junit" % "4.10" % Test
val mySql = "mysql" % "mysql-connector-java" % "5.1.41"

// Testing libraries for dealing with CompletionStage...
val assertCore = "org.assertj" % "assertj-core" % "3.6.2" % Test
val awaitility =  "org.awaitility" % "awaitility" % "2.0.0" % Test

// Make verbose tests
testOptions in Test := Seq(Tests.Argument(TestFrameworks.JUnit, "-a", "-v"))