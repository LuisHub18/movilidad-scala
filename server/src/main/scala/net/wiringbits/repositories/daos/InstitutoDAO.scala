package net.wiringbits.repositories.daos

import net.wiringbits.repositories.models.Instituto

import java.sql.Connection
import java.util.UUID

object InstitutoDAO {
  import anorm._

  def create(request: Instituto.Crear)(implicit conn: Connection): Unit = {
    val _ = SQL"""
      INSERT INTO instituto
        (id_instituto, nombre, domicilio, telefono)
      VALUES (
        ${request.idInstituto}::UUID,
        ${request.nombre},
        ${request.domicilio},
        ${request.telefono}
      )
      """
      .execute()
  }
  def all()(implicit conn: Connection): List[Instituto] = {
    SQL"""
      SELECT id_instituto, nombre, domicilio, telefono
      FROM instituto
      """
      .as(institutoParser.*)
  }
  def find(idInstituto: UUID)(implicit conn: Connection): Option[Instituto] = {
    SQL"""
      SELECT id_instituto, nombre, domicilio, telefono
      FROM instituto
      WHERE id_instituto = $idInstituto::UUID
      """
      .as(institutoParser.singleOpt)
  }
}
