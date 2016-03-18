package org.pwr.molczak.mio.utils.functions

object Condition {
  def isLessThanOne(sum: Double, threshold: Double): Int = {
    if (sum > threshold) 1 else 0
  }
}