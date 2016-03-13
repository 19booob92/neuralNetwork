package org.pwr.molczak.mio

import org.pwr.molczak.mio.perceptron.Perceptron
import org.pwr.molczak.mio.utils.Data
import org.pwr.molczak.mio.utils.functions.Sigma
import org.pwr.molczak.mio.utils.functions.Condition

object Runner {

  def main(args: Array[String]): Unit = {
    val perception = new Perceptron(Data.loadCSV("/home/booob/Develop/SCALA/SieciNeuronowe/BazaDoNauki.csv"))

    for (i <- 0 to 100)
      perception.learn(Sigma.sigmoidium, Condition.isLessThanOne)

    print(perception.define(Sigma.sigmoidium, Condition.isLessThanOne, Array(60, -15, 0, 0, 5, 5, 1000, 0, 0, -15)))
  }
}