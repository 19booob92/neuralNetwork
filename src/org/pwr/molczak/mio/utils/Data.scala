package org.pwr.molczak.mio.utils

object Data {

  def loadCSV(path: String): Array[Parameter] = {
    io.Source.fromFile(path)
      .getLines()
      .map { x =>
        {
          val data = x.split(",")
          val length = data.length
          new Parameter(data.slice(0, length - 2).map(_.toInt), data(length - 1).toInt)
        }
      }.toArray
  }
}