import sbtcrossproject.CrossPlugin.autoImport.{CrossType, crossProject}

ThisBuild / baseVersion := "0.1"

ThisBuild / organization := "org.typelevel"
ThisBuild / organizationName := "Typelevel"

ThisBuild / homepage := Some(url("https://github.com/typelevel/spotted-leopards"))
ThisBuild / startYear := Some(2019)

ThisBuild / strictSemVer := false

ThisBuild / spiewakMainBranches := List("main")

ThisBuild / scmInfo := Some(
  ScmInfo(url("https://github.com/typelevel/spotted-leopards"), "git@github.com:typelevel/spotted-leopards.git")
)

ThisBuild / licenses := List("Apache-2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0"))

ThisBuild / publishGithubUser := "mpilquist"
ThisBuild / publishFullName := "Michael Pilquist"

ThisBuild / scalaVersion := "3.1.1-RC1"

lazy val root = project
  .in(file("."))
  .aggregate(core.jvm, core.js)
  .enablePlugins(NoPublishPlugin, SonatypeCiReleasePlugin)

lazy val core = crossProject(JVMPlatform, JSPlatform)
  .settings(
    name := "spotted-leopards",
    libraryDependencies += "org.scalameta" %%% "munit-scalacheck" % "0.7.29"
  )
