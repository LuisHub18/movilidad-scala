package net.wiringbits.repositories.daos

import net.wiringbits.repositories.models.CarreraInstituto

import java.sql.Connection
import java.util.UUID
import scala.concurrent.Future

object CarreraInstitutoDAO {
  import anorm._

  def findByInstituto(idInstituto: UUID)(implicit conn: Connection): List[CarreraInstituto] = {
    SQL"""
          SELECT id_carrera, id_instituto
          FROM carrera_instituto
          WHERE id_instituto = ${idInstituto.toString}::UUID
        """.as(carreraInstitutoParser.*)
  }
}
