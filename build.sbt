import sbtcrossproject.CrossPlugin.autoImport.{CrossType, crossProject}

ThisBuild / tlBaseVersion := "0.2"

ThisBuild / organization := "org.typelevel"
ThisBuild / organizationName := "Typelevel"

ThisBuild / homepage := Some(url("https://github.com/typelevel/spotted-leopards"))
ThisBuild / startYear := Some(2019)

ThisBuild / scmInfo := Some(
  ScmInfo(
    url("https://github.com/typelevel/spotted-leopards"),
    "git@github.com:typelevel/spotted-leopards.git"
  )
)

ThisBuild / licenses := List("Apache-2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0"))

ThisBuild / developers ++= List(
  tlGitHubDev("mpilquist", "Michael Pilquist")
)

ThisBuild / scalaVersion := "3.4.0-RC2"

lazy val root = tlCrossRootProject
  .aggregate(core)

lazy val core = crossProject(JVMPlatform, JSPlatform)
  .settings(
    name := "spotted-leopards",
    libraryDependencies += "org.scalameta" %%% "munit-scalacheck" % "1.0.0-M11",
    Compile / console / scalacOptions := (Compile / console / scalacOptions).value
      .filterNot(_.startsWith("-Wunused"))
  )
