package org.pwr.molczak.mio.utils.functions

object Sigma {
  val IPSILON = 2.71

  def sigmoidium(x: Double): Double = {

    val denominator = 1.0 + Math.pow(IPSILON, -x)

    return 1.0 / denominator
  }
}