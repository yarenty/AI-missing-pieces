package com.yarenty.charts

import java.awt.Color
import java.util.Date

import com.yarenty.utils.Helper
import org.jfree.data.time.{Minute, RegularTimePeriod}

import language.implicitConversions
import scalax.chart.api._

/**
  * Created by yarenty on 28/04/2017.
  */
object TimeSeriesCharts {


  def plotInTime(data: Seq[(Date, Double)], name: String): Unit = {
    implicit def jdate2jfree(d: Date): RegularTimePeriod = new Minute(d)

    implicit def theme = org.jfree.chart.StandardChartTheme.createDarknessTheme()

    val chart = XYLineChart(data.toTimePeriodValues(name))
    chart.plot.getRenderer().setSeriesPaint(0, Color.RED)
    chart.plot.setDomainGridlinePaint(Color.YELLOW)
    chart.plot.setRangeGridlinePaint(Color.YELLOW)
    chart.plot.setOutlinePaint(Color.GREEN)
    Helper.createOutputDirectory(name)
    chart.saveAsPNG(s"$name.png")

  }


  def createPNG(name: String, data: Seq[(String, Seq[(Double, Double)])]): Unit = {
    Helper.createOutputDirectory(name)
    val chart = XYLineChart(data)
    chart.saveAsPNG(s"$name.png")
  }


}
