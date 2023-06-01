package net.wiringbits.repositories.models

import java.util.UUID

case class Materia(
    id_materia: UUID,
    nombre: String
)

object Materia {
  case class CreateMateria(id_materia: UUID, nombre: String)
}
