package net.wiringbits.repositories

import net.wiringbits.executors.DatabaseExecutionContext
import net.wiringbits.repositories.daos.CarreraInstitutoDAO
import net.wiringbits.repositories.models.CarreraInstituto
import play.api.db.Database

import java.time.Clock
import java.util.UUID
import javax.inject.Inject
import scala.concurrent.Future

class CarreraInstitutoRepository @Inject() (
    database: Database
)(implicit ec: DatabaseExecutionContext) {
  def findByInstituto(idInstituto: UUID): Future[List[CarreraInstituto]] = Future {
    database.withConnection { implicit conn =>
      CarreraInstitutoDAO.findByInstituto(idInstituto)
    }
  }
}
