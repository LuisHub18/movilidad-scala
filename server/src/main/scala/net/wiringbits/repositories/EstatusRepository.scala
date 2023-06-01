package net.wiringbits.repositories

import net.wiringbits.executors.DatabaseExecutionContext
import net.wiringbits.repositories.daos.EstatusDAO
import net.wiringbits.repositories.models.Estatus
import play.api.db.Database

import java.util.UUID
import javax.inject.Inject
import scala.concurrent.Future

class EstatusRepository @Inject() (database: Database)(implicit ec: DatabaseExecutionContext) {

  def crear(request: Estatus.Crear): Future[Unit] = Future {
    database.withConnection { implicit conn =>
      EstatusDAO.create(request)
    }
  }

  def find(idStatus: UUID): Future[Option[Estatus]] = Future {
    database.withConnection { implicit conn =>
      EstatusDAO.find(idStatus)
    }
  }

  def all(): Future[List[Estatus]] = Future {
    database.withConnection { implicit conn =>
      EstatusDAO.all()
    }
  }

}
