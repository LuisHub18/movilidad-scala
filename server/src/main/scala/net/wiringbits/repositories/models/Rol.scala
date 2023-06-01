package net.wiringbits.repositories.models

import java.util.UUID

case class Rol (
  id_rol: UUID,
  tipo: String
)
object Rol {
  case class CreateRol(id_rol: UUID, tipo: String)
}
