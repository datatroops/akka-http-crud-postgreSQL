package io.datatroops

import akka.actor.{ActorRef, ActorSystem}
import akka.http.scaladsl.Http
import io.datatroops.route.Service
import io.datatroops.utils._
import slick.jdbc.JdbcBackend.Database
import slick.util.{AsyncExecutor, ClassLoaderUtil}

import scala.language.postfixOps
import scala.util.{Failure, Success}

object StartStockApp extends App with Service {
  print("Hello")

  implicit val system: ActorSystem = ActorSystem("StartApp")

  import system.dispatcher

  val database = Database.forURL(url = "jdbc:postgres://localHost:5432/postgres", null, null, null, null, AsyncExecutor.default(), keepAliveConnection = false, ClassLoaderUtil.defaultClassLoader)

  val postgres: PostgresSource = new PostgresSource(database)
  val campaignActor: ActorRef = system.actorOf(CRUDActor.props(postgres), "crud-operation")
  val binding = Http().newServerAt("localhost", 8081).bind(routes(campaignActor))

  binding.onComplete { case Success(binding) ⇒ val localAddress = binding.localAddress
    println(
      """
        |──────────────────────────────────────────────────────────────────────────────────────────────────────────
        |─██████████████─██████████████─████████████████───██████──██████─██████████─██████████████─██████████████─
        |─██░░░░░░░░░░██─██░░░░░░░░░░██─██░░░░░░░░░░░░██───██░░██──██░░██─██░░░░░░██─██░░░░░░░░░░██─██░░░░░░░░░░██─
        |─██░░██████████─██░░██████████─██░░████████░░██───██░░██──██░░██─████░░████─██░░██████████─██░░██████████─
        |─██░░██─────────██░░██─────────██░░██────██░░██───██░░██──██░░██───██░░██───██░░██─────────██░░██─────────
        |─██░░██████████─██░░██████████─██░░████████░░██───██░░██──██░░██───██░░██───██░░██─────────██░░██████████─
        |─██░░░░░░░░░░██─██░░░░░░░░░░██─██░░░░░░░░░░░░██───██░░██──██░░██───██░░██───██░░██─────────██░░░░░░░░░░██─
        |─██████████░░██─██░░██████████─██░░██████░░████───██░░██──██░░██───██░░██───██░░██─────────██░░██████████─
        |─────────██░░██─██░░██─────────██░░██──██░░██─────██░░░░██░░░░██───██░░██───██░░██─────────██░░██─────────
        |─██████████░░██─██░░██████████─██░░██──██░░██████─████░░░░░░████─████░░████─██░░██████████─██░░██████████─
        |─██░░░░░░░░░░██─██░░░░░░░░░░██─██░░██──██░░░░░░██───████░░████───██░░░░░░██─██░░░░░░░░░░██─██░░░░░░░░░░██─
        |─██████████████─██████████████─██████──██████████─────██████─────██████████─██████████████─██████████████─
        |──────────────────────────────────────────────────────────────────────────────────────────────────────────
        |""".stripMargin)
    print(s"Server is listening on ${localAddress.getHostName}:${localAddress.getPort}")
  case Failure(e) ⇒ print(s"Binding failed with ${e.getMessage}")
    system.terminate()
  }
}
