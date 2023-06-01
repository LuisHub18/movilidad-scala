package net.wiringbits.repositories

import net.wiringbits.executors.DatabaseExecutionContext
import net.wiringbits.repositories.daos.MateriaCarreraDAO
import net.wiringbits.repositories.models.MateriaCarrera
import play.api.db.Database

import java.util.UUID
import javax.inject.Inject
import scala.concurrent.Future

class MateriaCarreraRepository @Inject() (
    database: Database
)(implicit ec: DatabaseExecutionContext) {
  def all(): Future[List[MateriaCarrera]] = Future {
    database.withConnection { implicit conn =>
      MateriaCarreraDAO.all()
    }
  }

  def findByCarrera(idCarrera: UUID): Future[List[MateriaCarrera]] = Future {
    database.withConnection { implicit conn =>
      MateriaCarreraDAO.findByCarrera(idCarrera)
    }
  }
}
