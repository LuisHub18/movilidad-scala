package net.wiringbits.repositories

import net.wiringbits.config.UserTokensConfig
import net.wiringbits.executors.DatabaseExecutionContext
import net.wiringbits.repositories.daos.MateriaDAO
import net.wiringbits.repositories.models._
import play.api.db.Database
import java.time.Clock
import java.util.UUID
import javax.inject.Inject
import scala.concurrent.Future

class MateriaRepository @Inject() (
    database: Database
)(implicit
    ec: DatabaseExecutionContext,
    clock: Clock
) {

  def create(request: Materia.CreateMateria): Future[Unit] = Future {
    database.withTransaction { implicit conn =>
      MateriaDAO.create(request)
    }
  }

  def all(): Future[List[Materia]] = Future {
    database.withConnection { implicit conn =>
      MateriaDAO.all()
    }
  }

  def find(id_materia: UUID): Future[Option[Materia]] = Future {
    database.withConnection { implicit conn =>
      MateriaDAO.find(id_materia)
    }
  }
}
