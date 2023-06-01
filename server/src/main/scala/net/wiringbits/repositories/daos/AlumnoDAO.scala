package net.wiringbits.repositories.daos

import net.wiringbits.repositories.models.Alumno

import java.sql.Connection
import java.util.UUID

object AlumnoDAO {

  import anorm._

  def create(request: Alumno.Crear)(implicit conn: Connection): Unit = {
    val _ = SQL"""
        INSERT INTO alumno
            (id_alumno, semestre, num_movilidades, deuda, user_id)
        VALUES (
            ${request.idAlumno}::UUID,
            ${request.semestre},
            ${request.numMovilidades},
            ${request.deuda},
            ${request.idUsuario}::UUID
        )
        """
      .execute()
  }

  def find(id: UUID)(implicit conn: Connection): Option[Alumno] = {
    SQL"""
        SELECT id_alumno, semestre, num_movilidades, deuda, user_id
        FROM alumno
        WHERE id_alumno = $id::UUID
        """
      .as(alumnoParser.singleOpt)
  }

  def findByUser(userId: UUID)(implicit conn: Connection): Option[Alumno] = {
    SQL"""
        SELECT id_alumno, semestre, num_movilidades, deuda, user_id
        FROM alumno
        WHERE user_id = $userId::UUID
        """
      .as(alumnoParser.singleOpt)
  }

}
