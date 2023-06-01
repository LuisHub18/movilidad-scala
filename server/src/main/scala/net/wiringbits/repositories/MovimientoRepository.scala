package net.wiringbits.repositories

import net.wiringbits.executors.DatabaseExecutionContext
import net.wiringbits.repositories.daos.MovimientoDAO
import net.wiringbits.repositories.models.SolicitudMovilidad
import play.api.db.Database

import java.util.UUID
import javax.inject.Inject
import scala.concurrent.Future

class MovimientoRepository @Inject() (database: Database)(implicit ec: DatabaseExecutionContext) {
  def crear(request: SolicitudMovilidad.Crear): Future[Unit] = Future {
    database.withConnection { implicit conn =>
      MovimientoDAO.create(request)
    }
  }

  def find(idMovimiento: UUID): Future[Option[SolicitudMovilidad]] = Future {
    database.withConnection { implicit conn =>
      MovimientoDAO.find(idMovimiento)
    }
  }
}
