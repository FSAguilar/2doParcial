def procesarRango(
desde: Int,
hasta: Int,
transformar: Int => Int,
combinar: (Int, Int) => Int,
valorInicial: Int
): Int = {
    if (desde > hasta) valorInicial
    else {
        procesarRango(desde + 1, hasta, transformar, combinar, combinar(valorInicial, transformar(desde)))
    }
}

def buscar(
desde: Int,
hasta: Int,
condicion: Int => Boolean
): Int = {
    if (desde > hasta) -1
    else if (condicion(desde)) desde
    else buscar(desde + 1, hasta, condicion)
}
object main {

    def main(args: Array[String]): Unit = {
        
        def cantDivisores(numero: Int): Int = {
            procesarRango(1, numero, (x) => if (numero % x == 0) 1 else 0, (a, b) => a + b, 0)
        }
        //Primero
        def maxCantDivisores(desde: Int, hasta: Int): Int = {
            procesarRango(desde, hasta, cantDivisores, (a, b) => if (a > b) a else b, 0)
        }

        //Segundo
        def Primo(desde: Int, hasta: Int): Int = {
            buscar(desde, hasta, (numero: Int) => cantDivisores(numero) == 2)
        }
    }
}
