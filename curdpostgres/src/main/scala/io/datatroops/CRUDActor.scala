package io.datatroops

import akka.actor.{Actor, ActorLogging, Props}
import akka.http.scaladsl.model._
import akka.pattern.pipe
import io.datatroops.CRUDActor._
import io.datatroops.utils._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class CRUDActor(postgres: PostgresSource) extends Actor with ActorLogging {


  override def receive: Receive = {
    case TestApplication => Future.successful(HttpResponse(status = StatusCodes.OK, entity = "APPLICATION IS RUNNING PERFECTLY", protocol = HttpProtocols.`HTTP/1.0`)).pipeTo(sender())

    case GetData(companyname: String) =>
      val res = getData(companyname: String)
      res.pipeTo(sender())
  }


  def getData(companyname: String): Future[HttpResponse] = {
    val res = postgres.getData(companyname)
    Future.successful(HttpResponse(status = StatusCodes.OK, headers = Nil, entity = HttpEntity(ContentTypes.`application/json`, res.toString)))

  }
}

object CRUDActor {

  def props(postgres: PostgresSource): Props = Props(new CRUDActor(postgres))

  sealed trait ActorRequest

  sealed trait ActorResponse

  final case class TestApplication() extends ActorRequest

  final case class GetData(companyname: String) extends ActorRequest

}
