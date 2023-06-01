package net.wiringbits.repositories.daos

import net.wiringbits.repositories.models.MateriaCarrera

import java.sql.Connection
import java.util.UUID

object MateriaCarreraDAO {
  import anorm._

  def all()(implicit conn: Connection): List[MateriaCarrera] = {
    SQL"""
      SELECT id_carrera, id_materia
      FROM materia_carrera
    """.as(materiaCarreraParser.*)
  }

  def findByCarrera(idCarrera: UUID)(implicit conn: Connection): List[MateriaCarrera] = {
    SQL"""
      SELECT id_carrera, id_materia
      FROM materia_carrera
      WHERE id_carrera = $idCarrera::UUID
    """.as(materiaCarreraParser.*)
  }
}
