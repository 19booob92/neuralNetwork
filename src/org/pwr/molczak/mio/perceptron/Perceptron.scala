package org.pwr.molczak.mio.perceptron

import org.pwr.molczak.mio.utils.Parameter
import org.pwr.molczak.mio.utils.Break
import java.io.File
import java.io.FileWriter
import org.pwr.molczak.mio.utils.Break

class Perceptron(data: Array[Parameter]) {
  val fileToWrite = new FileWriter("/home/booob/Documents/test.csv", true)

  val random = scala.util.Random

  var threshold = 0.5

  val LEARNING_RATE = 0.1

  var weights = Array(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0)

  def learn(func: (Double) => (Double), condition: (Double, Double) => (Int)) = {
    try {
      for (i <- 1 to 1500) {
        var errors = 0

        for (actualExample <- data) {
          var sum = 0.0

          for { (parameter, weight) <- (actualExample.values zip weights) } {
            sum += parameter * weight
          }

          var diff = actualExample.expectedValue - condition(func(sum), threshold)

          if (diff != 0) {
            val newWeights = for {
              (parameter, weightIdx) <- (actualExample.values.zipWithIndex)
              newWeightValue = weights(weightIdx) + parameter * (diff * LEARNING_RATE)
            } yield newWeightValue

            weights = newWeights

//            threshold -= diff * LEARNING_RATE * 0.1

            errors += 1
          }
//          fileToWrite.write(weights.mkString(", "))
//          fileToWrite.write("\n")
        }
        if (errors == 0) {
          throw new Break()
        }
      }
    } finally {
      fileToWrite.close()
    }
  }

  def define(func: (Double) => (Double), condition: (Double, Double) => (Int), specificData: Array[Int]) = {
    var sum = 0.0

    for { (parameter, weight) <- (specificData zip weights) } {
      sum += parameter * weight
    }

    if (condition(func(sum), threshold) == 1) true else false
  }

}