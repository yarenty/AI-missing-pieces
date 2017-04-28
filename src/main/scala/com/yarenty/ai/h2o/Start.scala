package com.yarenty.ai.h2o

import water.H2OStarter

/**
  * Created by yarenty on 28/04/2017.
  */
class Start extends H2OStarter {

  val a = Array(
    "-name", "YOLO",
    "-ip", "127.0.0.1",
    "--ga_opt_out"
  )

  H2OStarter.start(a, System.getProperty("user.dir"))

}
