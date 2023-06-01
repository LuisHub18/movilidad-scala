package net.wiringbits.repositories.daos

import net.wiringbits.repositories.models.SolicitudMovilidad

import java.sql.Connection
import java.util.UUID

object SolicitudMovilidadDAO {
  import anorm._

  def create(request: SolicitudMovilidad.Crear)(implicit conn: Connection): Unit = {
    val _ = SQL"""
      INSERT INTO solicitud_movilidad
        (id_solicitud, id_alumno, fecha, descripcion, id_instituto)
      VALUES (
        ${request.idSolicitud}::UUID,
        ${request.idAlumno}::UUID,
        ${request.fecha},
        ${request.descripcion},
        ${request.idInstituto}::UUID
      )
      """
      .execute()
  }

  def find(id: UUID)(implicit conn: Connection): Option[SolicitudMovilidad] = {
    SQL"""
      SELECT id_solicitud, id_alumno, fecha, descripcion, id_instituto
      FROM solicitud_movilidad
      WHERE id_solicitud = $id::UUID
      """
      .as(solicitudMovilidadParser.singleOpt)
  }

  def findByAlumno(id: UUID)(implicit conn: Connection): List[SolicitudMovilidad] = {
    SQL"""
      SELECT id_solicitud, id_alumno, fecha, descripcion, id_instituto
      FROM solicitud_movilidad
      WHERE id_alumno = $id::UUID
      """
      .as(solicitudMovilidadParser.*)
  }
}
