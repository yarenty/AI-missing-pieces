package com.yarenty.utils

import java.io._
import java.nio.charset.StandardCharsets

import org.apache.commons.io.FilenameUtils
import water.fvec.{Frame, Vec}
import water.util.Log

import scala.reflect.io.Directory

/**
  *
  */
object Helper {


  def saveCSV(f: Frame, fileName: String): Unit = {
    Log.debug("CSV export::" + fileName)
    val csv = f.toCSV(true, false)
    val csv_writer = new PrintWriter(new File(fileName))
    while (csv.available() > 0) {
      csv_writer.write(csv.read.toChar)
    }
    csv_writer.close()
  }

  def saveString(f: String, fileName: String, force: Boolean = true): Unit = {
    createOutputDirectory(fileName, force)
    val stream: InputStream = new ByteArrayInputStream(f.getBytes(StandardCharsets.UTF_8))
    val string_writer = new PrintWriter(new File(fileName))
    while (stream.available() > 0) {
      string_writer.write(stream.read.toChar)
    }
    string_writer.close()
  }


  def createOutputDirectory(fileName: String, force: Boolean = false): Boolean = {
    val dir = FilenameUtils.getFullPathNoEndSeparator(fileName)
    Log.debug(s"Create output directory: $dir")
    println(s"Create output directory: $dir")
    val out = Directory(dir)
    out.createDirectory(force = force)
    if (force && !out.exists) {
      Log.err(s"Could not create output directory: $dir")
      println(s"Could not create output directory: $dir")
      System.exit(-1)
    }
    out.exists
  }


  def display[T](c: T, n: String): Unit = {
    c match {
      case _: Array[T] =>
        println(s"\n ${n.toUpperCase}")
        for (i <- c.asInstanceOf[Array[T]]) {
          print(i + ", ")
        }
        println
      case _ =>
        println(s"${n.toUpperCase}  =>  $c")
    }
  }

  def display[T](c: Array[Array[T]], n: String): Unit = {
    println(s"\n ${n.toUpperCase}")
    if (c != null)
      for (z <- c) {
        if (z != null) {
          for (i <- z) {
            print(i + ", ")
          }
          println
        }
      }
  }


  def vecToArray(v: Vec): Array[Double] = {
    val arr = Array.ofDim[Double](v.length.toInt)
    for (i <- 0 until v.length.toInt) {
      arr(i) = v.at(i)
    }
    arr
  }


  def vecToDateArray(v: Vec): Array[Long] = {
    val arr = Array.ofDim[Long](v.length.toInt)
    for (i <- 0 until v.length.toInt) {
      arr(i) = v.at8(i)
    }
    arr
  }

}
