package net.wiringbits.repositories

import net.wiringbits.executors.DatabaseExecutionContext
import net.wiringbits.repositories.daos.{CarreraDAO, MateriaDAO}
import net.wiringbits.repositories.models._
import play.api.db.Database

import java.time.Clock
import java.util.UUID
import javax.inject.Inject
import scala.concurrent.Future

class CarreraRepository @Inject() (
    database: Database
)(implicit
    ec: DatabaseExecutionContext,
    clock: Clock
) {

  def create(request: Carrera.CreateCarrera): Future[Unit] = Future {
    database.withTransaction { implicit conn =>
      CarreraDAO.create(request)
    }
  }

  def all(): Future[List[Carrera]] = Future {
    database.withConnection { implicit conn =>
      CarreraDAO.all()
    }
  }

  def find(id_carrera: UUID): Future[Option[Carrera]] = Future {
    database.withConnection { implicit conn =>
      CarreraDAO.find(id_carrera)
    }
  }
}
