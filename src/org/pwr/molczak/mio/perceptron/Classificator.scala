package org.pwr.molczak.mio.perceptron

object Classificator {
  def getClass(kolo : Boolean, kwadrat : Boolean, gwiazdka : Boolean) {
    if (kolo) println("Koło") 
    if (kwadrat) println("Kwadrat")
    if (gwiazdka) println("Gwiazdka")
    else if (!kolo && !gwiazdka && !kwadrat) println("Nie sklasyfikowano ! :(")
  }
}