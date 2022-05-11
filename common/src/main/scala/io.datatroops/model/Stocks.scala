package io.datatroops.model

import java.time.OffsetDateTime

//case class Stock(companies:List[Company])
case class Company(companyname: String, companyStockDetails: CompanyStockDetails)
case class CompanyStockDetails(sType: Option[String], symbol: String, open: Int, high: Double, low: Double, close: Double, ltp: Double,volume: Double,tsInMillis: OffsetDateTime, lowPriceRange: Int,highPriceRange: Double , totalBuyQty: Long,totalSellQty: Long, lastTradeQty: Int,lastTradeTime: OffsetDateTime )
