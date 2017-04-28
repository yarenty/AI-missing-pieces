package com.yarenty.ai.timeseries

import java.util.Date

import com.yarenty.charts.TimeSeriesCharts
import com.yarenty.utils.Helper
import org.jfree.data.time.{Minute, RegularTimePeriod}

import language.implicitConversions
import scala.util.Random

/**
  * Created by yarenty on 28/04/2017.
  */
object PrepareData extends App {
  implicit def jdate2jfree(d: Date): RegularTimePeriod = new Minute(d)


  val trainData = prepare(1,21)
  val testData = prepare(22,7)
  Helper.saveString(trainData, "/opt/workspace/AI-missing-pieces/src/main/resources/ts_train.csv", true)
  Helper.saveString(testData, "/opt/workspace/AI-missing-pieces/src/main/resources/ts_test.csv", true)


  def prepare(from:Int, days:Int):String = {

    val startDate = new Date(116, 3, from).getTime
    val timePeriod =  60 * 60 * 1000 //hour
    val initialData: Array[Double] = Array(7.0, 7.0, 7, 6, 5, 3, 3, 4, 4, 5, 6, 7, 8, 9, 9, 9, 10, 9, 9, 8, 7, 6, 6, 5)

    val data: Seq[(Date, Double)] =

      for (j <- 0 until days; i <- 0 until initialData.length) yield {
        new Date(startDate + (j * 24 + i) * timePeriod) -> (initialData(i) + Random.nextDouble() * 2 - {
          if (j % 5 == 0 || j % 5 == 0) 3.0 else 1.0
        })
      }

    //TimeSeriesCharts.plotInTime(data, "usage")

    val s = new  StringBuilder
    s.append("date,value\n")
    for ( (t,v) <-data){
      s.append(t.getTime).append(",").append(v).append("\n")
    }
    s.toString()
  }


}
