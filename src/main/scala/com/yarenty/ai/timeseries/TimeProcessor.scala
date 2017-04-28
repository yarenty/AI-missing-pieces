package com.yarenty.ai.timeseries

import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import water.fvec.Vec
import water.parser.BufferedString

/**
  * Created by yarenty on 28/04/2017.
  */
object TimeProcessor {


  def hour(date: Vec): Vec = {
    val hour = Vec.makeZero(date.length)
    val hourW = hour.open()

    date.get_type() match {
      case Vec.T_TIME =>
        for (i <- 0L until date.length) {
          val dt = new DateTime(date.at8(i))
          hourW.set(i, dt.getHourOfDay)
        }
    }
    hourW.close()
    hour
  }


  def day(date: Vec): Vec = {
    val day = Vec.makeZero(date.length)
    val dayW = day.open()

    date.get_type() match {
      case Vec.T_TIME =>
        for (i <- 0L until date.length) {
          val dt = new DateTime(date.at8(i))
          dayW.set(i, dt.getDayOfWeek)
        }
    }
    dayW.close()
    day
  }

  def week(date: Vec): Vec = {
    val week = Vec.makeZero(date.length)
    val weekW = week.open()

    date.get_type() match {
      case Vec.T_TIME =>
        for (i <- 0L until date.length) {
          val dt = new DateTime(date.at8(i))
          weekW.set(i, dt.getWeekyear)
        }
    }
    weekW.close()
    week
  }


}