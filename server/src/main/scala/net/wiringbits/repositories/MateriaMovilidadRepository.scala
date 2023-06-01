package net.wiringbits.repositories

import net.wiringbits.executors.DatabaseExecutionContext
import net.wiringbits.repositories.daos.MateriaMovilidadDAO
import net.wiringbits.repositories.models.MateriaMovilidad
import play.api.db.Database

import javax.inject.Inject
import scala.concurrent.Future

class MateriaMovilidadRepository @Inject() (
    database: Database
)(implicit ec: DatabaseExecutionContext) {
  def create(request: MateriaMovilidad.Crear): Future[Unit] = Future {
    database.withConnection { implicit conn =>
      MateriaMovilidadDAO.create(request)
    }
  }
}
