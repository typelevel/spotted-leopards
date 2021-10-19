ThisBuild / organization := "org.typelevel"

ThisBuild / scalaVersion := "3.1.0"

lazy val root = project
  .in(file("."))
  .settings(
    libraryDependencies += "org.scalameta" %% "munit-scalacheck" % "0.7.25"
  )