package io.datatroops.route

import akka.actor.ActorRef
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.server.Directives.{complete, extractRequest, get, parameters, path, pathPrefix}
import akka.http.scaladsl.server.{Route, RouteConcatenation}
import io.datatroops.handler.RouteHandler


trait Service extends RouteConcatenation with SprayJsonSupport with RouteHandler {
  def routes(command: ActorRef): Route = pathPrefix("stock") {
    extractRequest { headerRequest =>
      path("test_application_simple") {
        get {
          complete(testApplication(command))
        }
      } ~ path("get_data_from_db") {
        get {
          parameters("companyname") { companyname =>
            complete(getData(command,companyname))
          }
        }
      }
    }
  }
}