package io.datatroops.utils

import slick.jdbc.PostgresProfile.api._
import slick.lifted.{MappedProjection, ProvenShape}

import java.sql.Time

case class Company(companyname: String, companyStockDetails: CompanyStockDetails)

case class CompanyStockDetails(sType: String, symbol: String, open: Int, high: Double, low: Double, close: Double, ltp: Double, volume: Double, tsInMillis: Time, lowPriceRange: Int, highPriceRange: Double, totalBuyQty: Long, totalSellQty: Long, lastTradeQty: Int, lastTradeTime: Time)


final case class StockTable(tag: Tag) extends Table[Company](tag, Some(" "), "id") {

  def * : ProvenShape[Company] = (companyname, companyStockDetails).shaped <> (Company.tupled, Company.unapply)


  def companyname: Rep[String] = column[String]("companyname")

  def companyStockDetails: MappedProjection[CompanyStockDetails, (String, String, Int, Double, Double, Double, Double, Double, Time, Int, Double, Long, Long, Int, Time)] = (sType, symbol, open, high, low, close, ltp, volume, tsInMillis, lowPriceRange, highPriceRange, totalBuyQty, totalSellQty, lastTradeQty, lastTradeTime) <> (CompanyStockDetails.tupled, CompanyStockDetails.unapply)

  def sType: Rep[String] = column[String]("stype")

  def symbol: Rep[String] = column[String]("symbol")

  def open: Rep[Int] = column[Int]("open")

  def high: Rep[Double] = column[Double]("high")

  def low: Rep[Double] = column[Double]("low")

  def close: Rep[Double] = column[Double]("close")

  def ltp: Rep[Double] = column[Double]("ltp")

  def volume: Rep[Double] = column[Double]("volume")

  def tsInMillis: Rep[Time] = column[Time]("tsinmillis")

  def lowPriceRange: Rep[Int] = column[Int]("lowpricerange")

  def highPriceRange: Rep[Double] = column[Double]("highpricerange")

  def totalBuyQty: Rep[Long] = column[Long]("totalbuyqty")

  def totalSellQty: Rep[Long] = column[Long]("totalsellqty")

  def lastTradeQty: Rep[Int] = column[Int]("lasttradeqty")

  def lastTradeTime: Rep[Time] = column[Time]("lasttradetime")
}