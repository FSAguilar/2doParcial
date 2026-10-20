def crearTransformacion(
factor: Double,
desplazamiento: Double
): Double => Double
= {
  def f(x:Double):Double = { factor * x + desplazamiento }
  f
}

object main {
  def main(args: Array[String]): Unit = {
    val corregirError: Double => Double = crearTransformacion(1, -1.5)
    val calibrarSensor: Double => Double = crearTransformacion(1.04, 0)
    val convertirFahrenheit: Double => Double = crearTransformacion(1.8,32)

    val procesarTemperatura: Double => Double = corregirError.andThen(calibrarSensor).andThen(convertirFahrenheit)
    println(procesarTemperatura(20)) 
    println(procesarTemperatura(30)) 
    println(procesarTemperatura(10)) 
  }
}