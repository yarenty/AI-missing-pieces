package com.yarenty.ai.h2o

import java.io.File
import java.net.URI

import water.fvec.{H2OFrame, Vec}
import water.parser.{DefaultParserProviders, ParseSetup}
import water.util.Log

/**
  * Created by yarenty on 28/04/2017.
  */
object CSVParser {

  def loadData(file:String): H2OFrame = {
    val _tFP1 = System.nanoTime()
    val in = new H2OFrame(getParser, new URI("/opt/workspace/AI-missing-pieces/src/main/resources/"+file))
    val _timeFP = System.nanoTime() - _tFP1
    Log.info(s"TIME for processing file: ${_timeFP} (${_timeFP / 1000000000}sec)")
    in
  }


  def getParser: ParseSetup = {
    val p: ParseSetup = new ParseSetup()
    p.setParseType(DefaultParserProviders.CSV_INFO)
    p.setColumnTypes(Array(Vec.T_TIME, Vec.T_NUM))
    p.setSeparator(44)
    p.setSingleQuotes(false)
    p.setCheckHeader(1)
    p
  }

}
