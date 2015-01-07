import com.typesafe.sbt.SbtNativePackager._
import com.typesafe.sbt.packager.Keys._

packageArchetype.java_application

name := """AkkaSocketServer"""

version := "1.0"

scalaVersion := "2.10.4"

mainClass in Compile := Some("com.example.ApplicationMain")

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.3.6",
  "com.typesafe.akka" %% "akka-testkit" % "2.3.6" % "test",
  "org.scalatest" %% "scalatest" % "2.1.6" % "test")
  