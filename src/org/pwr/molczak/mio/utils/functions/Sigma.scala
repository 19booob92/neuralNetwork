package org.pwr.molczak.mio.utils.functions

object Sigma {
  val IPSILON = BigDecimal(2.71).setScale(100, BigDecimal.RoundingMode.HALF_UP)

  def sigmoidium(x: Double): Double = {


    val power = Math.pow(IPSILON.toDouble, -0.07 * x)
    
    val denominator = BigDecimal(1.0 + power).setScale(100, BigDecimal.RoundingMode.HALF_UP)

    val result = BigDecimal(1.0).setScale(100, BigDecimal.RoundingMode.HALF_UP) / denominator

    return result.toDouble
  }
}