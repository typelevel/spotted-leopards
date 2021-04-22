ThisBuild / organization := "org.typelevel"

ThisBuild / scalaVersion := "3.0.0-RC3"

lazy val root = project
  .in(file("."))
  .settings(
    libraryDependencies += "org.scalameta" %% "munit-scalacheck" % "0.7.25"
  )