package io.datatroops.handler

import akka.actor.ActorRef
import akka.http.scaladsl.model.HttpResponse
import akka.pattern.ask
import akka.util.Timeout
import io.datatroops.CRUDActor._

import scala.concurrent.Future
import scala.concurrent.duration._
import scala.language.postfixOps

trait RouteHandler {

  implicit val timeout: Timeout = Timeout(40 seconds)

  def testApplication(command: ActorRef): Future[HttpResponse] = {
    ask(command, TestApplication).mapTo[HttpResponse]
  }
def getData(command: ActorRef, companyname: String): Future[HttpResponse] = {
  ask(command,GetData(companyname)).mapTo[HttpResponse]
}
}
