package net.wiringbits.repositories

import net.wiringbits.executors.DatabaseExecutionContext
import net.wiringbits.repositories.daos.AlumnoDAO
import net.wiringbits.repositories.models.Alumno
import play.api.db.Database

import java.util.UUID
import javax.inject.Inject
import scala.concurrent.Future

class AlumnoRepository @Inject() (database: Database)(implicit ec: DatabaseExecutionContext) {

  def crear(request: Alumno.Crear): Future[Unit] = Future {
    database.withConnection { implicit conn =>
      AlumnoDAO.create(request)
    }
  }

  def find(idAlumno: UUID): Future[Option[Alumno]] = Future {
    database.withConnection { implicit conn =>
      AlumnoDAO.find(idAlumno)
    }
  }

  def findByUser(idUser: UUID): Future[Option[Alumno]] = Future {
    database.withConnection { implicit conn =>
      AlumnoDAO.findByUser(idUser)
    }
  }

}
