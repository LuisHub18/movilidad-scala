package net.wiringbits.repositories.daos

import net.wiringbits.repositories.models.MateriaMovilidad

import java.sql.Connection

object MateriaMovilidadDAO {
  import anorm._

  def all()(implicit conn: Connection): List[MateriaMovilidad] = {
    SQL"""
      SELECT id_materia, id_solicitud, calificacion
      FROM materia_movilidad
    """.as(materiaMovilidadParser.*)
  }

  def create(request: MateriaMovilidad.Crear)(implicit conn: Connection): Unit = {
    val _ =
      SQL"""
        INSERT INTO materia_movilidad (id_materia, id_solicitud, calificacion)
        VALUES (
          ${request.idMateria}::UUID,
          ${request.idSolicitud}::UUID,
          ${request.calificacion}
        )
         """.execute()
  }
}
