package org.pwr.molczak.mio

import org.pwr.molczak.mio.perceptron.Perceptron
import org.pwr.molczak.mio.utils.Data
import org.pwr.molczak.mio.utils.functions.Sigma
import org.pwr.molczak.mio.utils.functions.Condition
import org.pwr.molczak.mio.utils.Break

object Runner {

  def main(args: Array[String]): Unit = {
    val perception = new Perceptron(Data.loadCSV("/home/booob/Develop/SCALA/SieciNeuronowe/BazaDoNauki.csv"))

    try {
      perception.learn(Sigma.sigmoidium, Condition.isLessThanOne)
    } finally {
    	println(perception.define(Sigma.sigmoidium, Condition.isLessThanOne, Array(60, -10, 0, 0, 5, 5, 100, 0, 0, -15)))
    	println(perception.define(Sigma.sigmoidium, Condition.isLessThanOne, Array(60, -10, 0, 0, 5, 5, 100, 0, 0, -15)))
      println(perception.define(Sigma.sigmoidium, Condition.isLessThanOne, Array(60, 30, 0, 0, 5, 5, 100, 0, 0, 15)))
      println(perception.define(Sigma.sigmoidium, Condition.isLessThanOne, Array(60, 11, 0, 0, 5, 5, 100, 0, 0, 10)))
      println(perception.define(Sigma.sigmoidium, Condition.isLessThanOne, Array(60, 9, 0, 0, 5, 5, 100, 0, 0, 10)))
      println(perception.define(Sigma.sigmoidium, Condition.isLessThanOne, Array(60, 7, 0, 0, 5, 5, 100, 0, 0, 10)))
      println(perception.define(Sigma.sigmoidium, Condition.isLessThanOne, Array(60, 11, 12, 0, 5, 5, 100, 0, 0, 10)))
    }
  }
}