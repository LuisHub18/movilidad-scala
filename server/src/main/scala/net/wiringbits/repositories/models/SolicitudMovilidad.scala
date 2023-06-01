package net.wiringbits.repositories.models

import java.time.Instant
import java.util.UUID

case class SolicitudMovilidad(
    idSolicitudMovilidad: UUID,
    idAlumno: UUID,
    fecha: Instant,
    descripcion: String,
    idInstituto: UUID
)

object SolicitudMovilidad {
  case class Crear(
      idSolicitud: UUID,
      idAlumno: UUID,
      fecha: Instant,
      descripcion: String,
      idInstituto: UUID
  )
}
