import sbt._

trait Versions {
  val akkaVersion = "2.6.18"
  val akkaHttpVersion = "10.2.7"
  val akkaHttpCirceVersion="1.39.2"
  val circeVersion = "0.10.0"
}

object Dependencies extends Versions {

  //common dependencies
  val logging = "log4j" % "log4j" % "1.2.16"

  //akka dependencies
  val actor = "com.typesafe.akka" %% "akka-actor" % akkaVersion
  val akkaStream = "com.typesafe.akka" %% "akka-stream" % akkaVersion
  val akkaHttp = "com.typesafe.akka" %% "akka-http" % akkaHttpVersion
  val akkaHttpCirce= "de.heikoseeberger" %% "akka-http-circe" % akkaHttpCirceVersion
  val sprayJson = "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion

  //db dependencies
    val postgresSQL =  "org.postgresql" % "postgresql" % "42.2.14"
 val slick ="com.typesafe.slick" %% "slick" % "3.3.2"
  val slf= "org.slf4j" % "slf4j-nop" % "1.6.4"


  // circe for paring
  val circe: Seq[ModuleID] = Seq(
    "io.circe" %% "circe-core",
    "io.circe" %% "circe-generic",
    "io.circe" %% "circe-parser"
  ).map(_ % circeVersion)

  val commonDependencies = List(
    sprayJson,
    logging,
  ) ++ circe

  val dbDependencies = List(postgresSQL, slick, slf)

  val serviceDependencies =List(actor,akkaStream,akkaHttp,akkaHttpCirce)

}
