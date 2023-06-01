package net.wiringbits.repositories.daos

import net.wiringbits.common.models.Email
import net.wiringbits.repositories.models.{Materia, User}

import java.sql.Connection
import java.util.UUID

object MateriaDAO {

  import anorm._

  def create(request: Materia.CreateMateria)(implicit conn: Connection): Unit = {
    val _ =
      SQL"""
          INSERT INTO materia
          (id_materia, nombre)
          VALUES (
            ${request.id_materia.toString}::UUID,
            ${request.nombre}
          )
        """
        .execute()
  }

  def all()(implicit conn: Connection): List[Materia] = {
    SQL"""
          SELECT id_materia, nombre
          FROM materia
        """.as(materiaParser.*)
  }

  def find(UUID: UUID)(implicit conn: Connection): Option[Materia] = {
    SQL"""
          SELECT id_materia, nombre
          FROM materia
          WHERE id_materia = ${UUID.toString}::UUID
        """.as(materiaParser.singleOpt)
  }
}
