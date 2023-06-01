package net.wiringbits.repositories.models

import java.util.UUID

case class Estatus(idEstatus: UUID, descripcion: String)

object Estatus {
  case class Crear(idEstatus: UUID, descripcion: String)
}
