package net.wiringbits.repositories

import net.wiringbits.executors.DatabaseExecutionContext
import net.wiringbits.repositories.daos.{CarreraDAO, MateriaDAO, RolDAO}
import net.wiringbits.repositories.models._
import play.api.db.Database

import java.time.Clock
import java.util.UUID
import javax.inject.Inject
import scala.concurrent.Future

class RolRepository @Inject() (
  database: Database
)(implicit
  ec: DatabaseExecutionContext,
  clock: Clock
) {

  def create(request: Rol.CreateRol): Future[Unit] = Future {
    database.withTransaction { implicit conn =>
      RolDAO.create(request)
    }
  }

  def all(): Future[List[Rol]] = Future {
    database.withConnection { implicit conn =>
      RolDAO.all()
    }
  }

  def find(id_rol: UUID): Future[Option[Rol]] = Future {
    database.withConnection { implicit conn =>
      RolDAO.find(id_rol)
    }
  }
}
