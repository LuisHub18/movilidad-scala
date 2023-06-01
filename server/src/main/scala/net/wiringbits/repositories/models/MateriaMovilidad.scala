package net.wiringbits.repositories.models

import java.util.UUID

case class MateriaMovilidad(idSolicitud: UUID, idMateria: UUID, calificacion: Double)

object MateriaMovilidad {
  case class Crear(idSolicitud: UUID, idMateria: UUID, calificacion: Double = 0)
}
