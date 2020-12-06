ThisBuild / scalaVersion     := "2.11.12"
ThisBuild / version          := "0.1.0"
ThisBuild / organization     := "com.julius"
ThisBuild / organizationName := "julius"

val scalaTest = "org.scalatest" %% "scalatest" % "3.0.5"

lazy val root = (project in file("."))
  .settings(
    name := "spark-practice",
    libraryDependencies += scalaTest % Test,
    libraryDependencies += "org.apache.spark" % "spark-core_2.11" % "2.4.7" % "provided",
    libraryDependencies += "org.apache.hadoop" % "hadoop-streaming" % "2.7.0"
  )



// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.