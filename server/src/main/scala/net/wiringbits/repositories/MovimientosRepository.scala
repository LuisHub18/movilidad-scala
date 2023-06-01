package net.wiringbits.repositories

import net.wiringbits.executors.DatabaseExecutionContext
import net.wiringbits.repositories.daos.MovimientosDAO
import net.wiringbits.repositories.models.Movimientos
import play.api.db.Database

import java.time.Clock
import javax.inject.Inject
import scala.concurrent.Future

class MovimientosRepository @Inject() (
    database: Database
)(implicit
    ec: DatabaseExecutionContext
) {
  def create(request: Movimientos.Crear): Future[Unit] = Future {
    database.withTransaction { implicit conn =>
      MovimientosDAO.create(request)
    }
  }

  def all(): Future[List[Movimientos]] = Future {
    database.withConnection { implicit conn =>
      MovimientosDAO.all()
    }
  }
}
