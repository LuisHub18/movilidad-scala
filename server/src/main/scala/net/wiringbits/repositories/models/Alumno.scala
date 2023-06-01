package net.wiringbits.repositories.models

import java.util.UUID

case class Alumno(idAlumno: UUID, semestre: Int, numMovilidades: Int, deuda: Boolean, idUsuario: UUID)

object Alumno {
  case class Crear(idAlumno: UUID, semestre: Int, numMovilidades: Int, deuda: Boolean, idUsuario: UUID)
}
