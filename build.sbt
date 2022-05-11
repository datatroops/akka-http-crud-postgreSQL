import Dependencies._

name := "AkkaHttpCRUDPostgresSQL"

version := "0.1"

scalaVersion := "2.13.8"

lazy val commonSettings = Seq(
  organization := "io.datatroops",
  version := "0.1"
)
lazy val common = (project in file("common"))
  .settings(
    libraryDependencies ++= commonDependencies,
    commonSettings
  )
lazy val persistence = (project in file("persistence"))
  .settings(
    libraryDependencies ++= commonDependencies ++ dbDependencies,
    commonSettings
  ).dependsOn(common)
lazy val curdpostgres = (project in file("curdpostgres"))
  .settings(
    libraryDependencies ++= commonDependencies ++ serviceDependencies,
    commonSettings
  ).dependsOn(common, persistence)