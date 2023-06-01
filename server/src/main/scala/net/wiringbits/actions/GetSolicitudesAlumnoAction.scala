package net.wiringbits.actions

import net.wiringbits.api.models.GetSolicitudesAlumno
import net.wiringbits.repositories.{AlumnoRepository, InstitutoRepository, SolicitudMovilidadRepository}

import java.util.UUID
import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

class GetSolicitudesAlumnoAction @Inject() (
    solicitudMovilidadRepository: SolicitudMovilidadRepository,
    institutoRepository: InstitutoRepository,
    alumnoRepository: AlumnoRepository
)(implicit ec: ExecutionContext) {
  def apply(userId: UUID): Future[GetSolicitudesAlumno.Response] = {
    for {
      alumno <- alumnoRepository.findByUser(userId).map(_.getOrElse(throw new Exception("Alumno no encontrado")))
      solicitudMovilidad <- solicitudMovilidadRepository.findByAlumno(alumno.idAlumno)
      pene <- Future.sequence(
        solicitudMovilidad.map(x =>
          institutoRepository
            .find(x.idInstituto)
            .map(_.getOrElse(throw new Exception("Instituto no encontrado")))
            .map(y =>
              GetSolicitudesAlumno.Response.SolicitudMovilidad(
                idSolicitudMovilidad = x.idSolicitudMovilidad,
                idAlumno = x.idAlumno,
                fecha = x.fecha,
                descripcion = x.descripcion,
                instituto = y.nombre
              )
            )
        )
      )
    } yield GetSolicitudesAlumno.Response(pene)
  }
}
