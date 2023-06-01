package net.wiringbits.repositories.daos

import net.wiringbits.repositories.models.Estatus

import java.sql.Connection
import java.util.UUID

object EstatusDAO {
  import anorm._

  def create(request: Estatus.Crear)(implicit conn: Connection): Unit = {
    val _ = SQL"""
      INSERT INTO estatus
        (id_estatus, descripcion)
      VALUES (
        ${request.idEstatus}::UUID,
        ${request.descripcion}
      )
      """
      .execute()
  }

  def find(id: UUID)(implicit conn: Connection): Option[Estatus] = {
    SQL"""
      SELECT id_estatus, descripcion
      FROM estatus
      WHERE id_estatus = $id::UUID
      """
      .as(estatusParser.singleOpt)
  }
}
