package net.wiringbits.repositories.models

import java.util.UUID

case class Carrera (
  id_carrera: UUID,
  nombre: String
)
object Carrera {
  case class CreateCarrera(id_carrera: UUID, nombre: String)
}
