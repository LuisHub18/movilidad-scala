package net.wiringbits.repositories.models

import net.wiringbits.webapp.utils.admin.config.PrimaryKeyDataType.UUID

import java.time.Instant
import java.util.UUID

case class Movimientos(idSolicitudMovilidad: UUID, idUsuario: UUID, fechaMovimiento: Instant, idEstatus: UUID)

object Movimientos {
  case class Crear(idSolicitudMovilidad: UUID, idUsuario: UUID, fechaMovimiento: Instant, idEstatus: UUID)
}
