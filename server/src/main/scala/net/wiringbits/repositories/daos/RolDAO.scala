package net.wiringbits.repositories.daos

import net.wiringbits.common.models.Email
import net.wiringbits.repositories.models.{Materia, Rol, User}

import java.sql.Connection
import java.util.UUID

object RolDAO {

  import anorm._

  def create(request: Rol.CreateRol)(implicit conn: Connection): Unit = {
    val _ =
      SQL"""
          INSERT INTO rol
          (id_rol, tipo)
          VALUES (
            ${request.id_rol.toString}::UUID,
            ${request.tipo}
          )
        """
        .execute()
  }

  def all()(implicit conn: Connection): List[Rol] = {
    SQL"""
          SELECT id_rol, tipo
          FROM rol
        """.as(rolParser.*)
  }

  def find(UUID: UUID)(implicit conn: Connection): Option[Rol] = {
    SQL"""
          SELECT id_rol, tipo
          FROM rol
          WHERE id_rol = ${UUID.toString}::UUID
        """.as(rolParser.singleOpt)
  }
}
