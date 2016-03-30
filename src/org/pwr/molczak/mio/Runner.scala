package org.pwr.molczak.mio

import org.pwr.molczak.mio.perceptron.Perceptron
import org.pwr.molczak.mio.utils.Data
import org.pwr.molczak.mio.utils.functions.Sigma
import org.pwr.molczak.mio.utils.functions.Condition
import org.pwr.molczak.mio.utils.Break
import org.pwr.molczak.mio.perceptron.Classificator

object Runner {

  val EXAMPLE_CIRCLE = Array(1, 0, 0)
  val EXAMPLE_SQUARE = Array(4, 0, 4)
  val EXAMPLE_STAR = Array(12, 6, 0)
  val EXAMPLE_UNDEFINED = Array(0, 11, 10)

  def main(args: Array[String]): Unit = {
    val kolo = new Perceptron(Data.loadCSV("/home/booob/scalaWorkspace/NeuronNetwork/kolo.csv"))
    val gwiazdka = new Perceptron(Data.loadCSV("/home/booob/scalaWorkspace/NeuronNetwork/gwiazdka.csv"))
    val kwadrat = new Perceptron(Data.loadCSV("/home/booob/scalaWorkspace/NeuronNetwork/kwadrat.csv"))

    kolo.learn(Sigma.sigmoidium, Condition.isLessThanOne)
    gwiazdka.learn(Sigma.sigmoidium, Condition.isLessThanOne)
    kwadrat.learn(Sigma.sigmoidium, Condition.isLessThanOne)

    val isKolo = kolo.define(Sigma.sigmoidium, Condition.isLessThanOne, EXAMPLE_STAR)
    val isKwadrat = kwadrat.define(Sigma.sigmoidium, Condition.isLessThanOne, EXAMPLE_STAR)
    val isGwiazdka = gwiazdka.define(Sigma.sigmoidium, Condition.isLessThanOne, EXAMPLE_STAR)

    Classificator.getClass(isKolo, isKwadrat, isGwiazdka);

  }
}