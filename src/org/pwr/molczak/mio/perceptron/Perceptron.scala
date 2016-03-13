package org.pwr.molczak.mio.perceptron

import org.pwr.molczak.mio.utils.Parameter
import org.pwr.molczak.mio.utils.Parameter

class Perceptron(data: Array[Parameter]) {

  val random = scala.util.Random

  val DATA_SIZE = data.length

  val THRESHOLD = 50
  val LEARNING_RATE = 0.9

  var weights = Array(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0)

  def learn(func: (Double) => (Double), condition: (Double, Double) => (Int)): Boolean = {

    var sum = 0.0

    val inputDataIdx = random.nextInt(DATA_SIZE - 1)

    for { (parameter, weight) <- (data(inputDataIdx).values zip weights) } {
      sum += parameter * weight
    }

    val diff = (condition(func(sum), THRESHOLD) - data(inputDataIdx).expectedValue)

    if (diff != 0) {
      val newWeights = for {
        (parameter, weightIdx) <- (data(inputDataIdx).values.zipWithIndex)
        newWeightValue = weights(weightIdx) + (LEARNING_RATE * diff * parameter)
      } yield newWeightValue

      weights = newWeights
    }

    return false;
  }

  def define(func: (Double) => (Double), condition: (Double, Double) => (Int), specificData: Array[Int]) = {
    var sum = 0.0

    val inputDataIdx = random.nextInt(DATA_SIZE - 1)

    for { (parameter, weight) <- (specificData zip weights) } {
      sum += parameter * weight
    }

    if (condition(func(sum), THRESHOLD) == 1) true else false
  }

}