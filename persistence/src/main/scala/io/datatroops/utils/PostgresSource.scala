package io.datatroops.utils

import slick.jdbc.PostgresProfile.api._
import slick.lifted.TableQuery

import scala.concurrent.Future


class PostgresSource(db: Database) {


  def insertData(stock: Company): Future[Int] = {
    db.run(TableQuery[StockTable] += stock)
  }

  def getData(companyname: String): Future[Option[Company]] = {
    val query = TableQuery[StockTable].filter(f => f.companyname === companyname).result.headOption
    db.run(query)
  }

  def deleteData(companyname: String): Future[Int] = {
    val query = TableQuery[StockTable].filter(f => f.companyname === companyname).delete
    db.run(query)
  }

}