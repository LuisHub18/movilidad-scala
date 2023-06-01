package net.wiringbits.repositories.daos

import net.wiringbits.repositories.models.Carrera

import java.sql.Connection
import java.util.UUID

object CarreraDAO {
  import anorm._

  def create(request: Carrera.CreateCarrera)(implicit conn: Connection): Unit = {
    val _ =
      SQL"""
          INSERT INTO carrera
          (id_carrera, nombre)
          VALUES (
            ${request.id_carrera.toString}::UUID,
            ${request.nombre}
          )
        """
        .execute()
  }

  def all()(implicit conn: Connection): List[Carrera] = {
    SQL"""
          SELECT id_carrera, nombre
          FROM carrera
        """.as(carreraParser.*)
  }

  def find(UUID: UUID)(implicit conn: Connection): Option[Carrera] = {
    SQL"""
          SELECT id_carrera, nombre
          FROM carrera
          WHERE id_carrera = ${UUID.toString}::UUID
        """.as(carreraParser.singleOpt)
  }
}
