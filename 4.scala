abstract class Servicio(
    val tipo: String,
    val tarifaBase: Double
) {
  def obtenerCosto(): Double

  def mostrarCotizacion(): String =
    s"$tipo: ${obtenerCosto()}"

  def +(otro: Servicio): Double =
    obtenerCosto() + otro.obtenerCosto()
}

trait DescuentoEficiente {
  def ajustarPorAhorroEnergetico(costo: Double, bajoConsumo: Boolean): Double =
    if (bajoConsumo) costo * 0.9 else costo
}

class ServicioIluminacion(
    val cantidadLamparas: Int,
    val horasMontaje: Int,
    val bajoConsumo: Boolean,
    costoPorLamparaHora: Double
) extends Servicio("Iluminación", costoPorLamparaHora)
    with DescuentoEficiente {
  def obtenerCosto(): Double = {
    val costo = cantidadLamparas * horasMontaje * tarifaBase
    ajustarPorAhorroEnergetico(costo, bajoConsumo)
  }
}

object ServicioIluminacion {
  def apply(
      cantidadLamparas: Int,
      horasMontaje: Int,
      bajoConsumo: Boolean,
      costoPorLamparaHora: Double
  ): ServicioIluminacion =
    new ServicioIluminacion(
      if (cantidadLamparas > 0) cantidadLamparas else 0,
      if (horasMontaje > 0) horasMontaje else 0,
      bajoConsumo,
      if (costoPorLamparaHora >= 0) costoPorLamparaHora else 0.0
    )
}

class ServicioSeguridad(
    val cantidadGuardias: Int,
    val horasCobertura: Int,
    val uniformeEspecial: Boolean,
    costoPorGuardiaHora: Double
) extends Servicio("Seguridad", costoPorGuardiaHora) {
  def obtenerCosto(): Double = {
    val costoPersonal = cantidadGuardias * horasCobertura * tarifaBase
    if (uniformeEspecial) costoPersonal + 50.0 else costoPersonal
  }
}

object ServicioSeguridad {
  def apply(
      cantidadGuardias: Int,
      horasCobertura: Int,
      uniformeEspecial: Boolean,
      costoPorGuardiaHora: Double
  ): ServicioSeguridad =
    new ServicioSeguridad(
      if (cantidadGuardias > 0) cantidadGuardias else 0,
      if (horasCobertura > 0) horasCobertura else 0,
      uniformeEspecial,
      if (costoPorGuardiaHora >= 0) costoPorGuardiaHora else 0.0
    )
}

object main {
  def main(args: Array[String]): Unit = {
    val iluminacion:Servicio = ServicioIluminacion(20, 5, true, 3.0)
    val seguridad:Servicio = ServicioSeguridad(3, 6, true, 25.0)

    println(iluminacion.mostrarCotizacion())
    println(seguridad.mostrarCotizacion())

    val costoTotal = iluminacion + seguridad
    println(s"Costo combinado de los 2 servicios: $costoTotal")
  }
}
