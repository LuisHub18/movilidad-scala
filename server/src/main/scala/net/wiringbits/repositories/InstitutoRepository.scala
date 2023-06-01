package net.wiringbits.repositories

import net.wiringbits.executors.DatabaseExecutionContext
import net.wiringbits.repositories.daos.InstitutoDAO
import net.wiringbits.repositories.models.Instituto
import play.api.db.Database

import java.sql.Connection
import java.util.UUID
import javax.inject.Inject
import scala.concurrent.Future

class InstitutoRepository @Inject() (database: Database)(implicit ec: DatabaseExecutionContext) {
  def create(request: Instituto.Crear): Future[Unit] = Future {
    database.withConnection { implicit conn =>
      InstitutoDAO.create(request)
    }
  }

  def all(): Future[List[Instituto]]= Future{
    database.withConnection { implicit conn =>
      InstitutoDAO.all()
    }
  }

  def find(idInstituto: UUID): Future[Option[Instituto]] = Future {
    database.withConnection { implicit conn =>
      InstitutoDAO.find(idInstituto)
    }
  }

}
