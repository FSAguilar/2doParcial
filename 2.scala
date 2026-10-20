

object main {
  def main(args: Array[String]): Unit = {

    def crearValidador(minimo:Int)(maximo:Int)(numero:Int): Boolean = {
      numero >= minimo && numero <= maximo
    }

    def evaluar(numero:Int, validador: Int => Boolean): String = {
      if (validador(numero)) "VALIDO" else "INVALIDO"
    }

    val validarPorcentaje: Int => Boolean = crearValidador(0)(100)
    val validarEdadLaboral: Int => Boolean = crearValidador(18)(65)
    val validarNota: Int => Boolean = crearValidador(0)(10)

    println(evaluar(50, validarPorcentaje)) 
    println(evaluar(70, validarEdadLaboral)) 
    println(evaluar(34, validarEdadLaboral)) 
    println(evaluar(-10, validarNota))
  }
  
}
