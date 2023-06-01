package net.wiringbits.repositories.models

import java.util.UUID

case class Instituto(idInstituto: UUID, nombre: String, domicilio: String, telefono: String)

object Instituto {
  case class Crear(idInstituto: UUID, nombre: String, domicilio: String, telefono: String)
}
