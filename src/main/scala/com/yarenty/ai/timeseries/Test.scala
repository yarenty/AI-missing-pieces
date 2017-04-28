package com.yarenty.ai.timeseries

import com.yarenty.ai.h2o.{CSVParser, Start}
import hex.glm.{GLM, GLMModel}
import hex.glm.GLMModel.GLMParameters
import water.Key
import water.fvec.{Frame, H2OFrame}
import water.util.TwoDimTable


/**
  * Created by yarenty on 28/04/2017.
  */
object Test extends Start {


  def main(args: Array[String]) {

    val train = CSVParser.loadData("ts_train.csv")
    val test = CSVParser.loadData("ts_test.csv")

    glmModel(train, test)


    val train_withHour = new H2OFrame(new Frame(Key.make("trainhr").asInstanceOf[Key[Frame]], train._names :+ "hour", train.vecs() :+ TimeProcessor.hour(train.vec("date"))))
    val test_withHour = new H2OFrame(new Frame(Key.make("testhr").asInstanceOf[Key[Frame]], test._names :+ "hour", test.vecs() :+ TimeProcessor.hour(test.vec("date")))
    )


    glmModel(train_withHour, test_withHour)

    train.vec("hour").toCategoricalVec
    test.vec("hour").toCategoricalVec
    glmModel(train, test)
  }


  def glmModel(train: H2OFrame, test: H2OFrame): TwoDimTable = {
    println(train._names.mkString(","))
    val params = new GLMParameters()
    params._train = train.key
    params._valid = test.key
    params._response_column = "value"


    val key: Key[GLMModel] = Key.make().asInstanceOf[Key[GLMModel]]
    val gbm = new GLM(params, key)

    gbm.trainModel.get._output._model_summary
  }

}
