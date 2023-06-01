package net.wiringbits.actions

import net.wiringbits.api.models.{GetCoordinadorSolicitud, GetSolicitudesAlumno}
import net.wiringbits.repositories.{
  InstitutoRepository,
  MateriaCarreraRepository,
  MateriaMovilidadRepository,
  MateriaRepository,
  MovimientosRepository,
  SolicitudMovilidadRepository,
  UsersRepository
}

import java.util.UUID
import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

class GetCoordinadorSolicitudAction @Inject() (
    solicitudMovilidadRepository: SolicitudMovilidadRepository,
    usersRepository: UsersRepository,
    institutoRepository: InstitutoRepository,
    materiaRepository: MateriaRepository,
    materiaCarreraRepository: MateriaCarreraRepository,
    materiaMovilidadRepository: MateriaMovilidadRepository,
    movimientosRepository: MovimientosRepository
)(implicit ec: ExecutionContext) {
  def apply(userId: UUID): Future[GetCoordinadorSolicitud.Response] = {
    for {
      user <- usersRepository.find(userId).map(_.getOrElse(throw new Exception("User not found")))
      solicitudes <- solicitudMovilidadRepository.findAsd(user.idInstituto)
      materias <- materiaCarreraRepository.all().map(_.filter(_.idCarrera == user.idCarrera))
      caca <- materiaMovilidadRepository.all().map(_.filter(x => materias.map(_.idMateria).contains(x.idMateria)))
      fil = solicitudes.filter(x => caca.map(_.idSolicitud).contains(x.idSolicitudMovilidad))
      movimientos <- movimientosRepository.all()
      fil2 = fil.filterNot(x => movimientos.map(_.idSolicitudMovilidad).contains(x.idSolicitudMovilidad))
      pene <- Future.sequence(
        fil2.map(x =>
          institutoRepository
            .find(x.idInstituto)
            .map(_.getOrElse(throw new Exception("Instituto no encontrado")))
            .map(y =>
              GetCoordinadorSolicitud.Response.SolicitudMovilidad(
                idSolicitudMovilidad = x.idSolicitudMovilidad,
                idAlumno = x.idAlumno,
                fecha = x.fecha,
                descripcion = x.descripcion,
                instituto = y.nombre
              )
            )
        )
      )
    } yield GetCoordinadorSolicitud.Response(pene)
  }
}
