package net.wiringbits.actions

import net.wiringbits.api.models.{CrearSolicitud, CreateUser}
import net.wiringbits.apis.ReCaptchaApi
import net.wiringbits.config.UserTokensConfig
import net.wiringbits.repositories
import net.wiringbits.repositories.{
  AlumnoRepository,
  MateriaMovilidadRepository,
  SolicitudMovilidadRepository,
  UsersRepository
}
import net.wiringbits.repositories.models.{MateriaMovilidad, User}
import net.wiringbits.util.{EmailsHelper, TokenGenerator, TokensHelper}
import net.wiringbits.validations.{ValidateCaptcha, ValidateEmailIsAvailable}
import org.mindrot.jbcrypt.BCrypt

import java.time.Instant
import java.util.UUID
import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

class CrearSolicitudAction @Inject() (
    solicitudMovilidadRepository: SolicitudMovilidadRepository,
    materiaMovilidadRepository: MateriaMovilidadRepository,
    alumnoRepository: AlumnoRepository
)(implicit
    ec: ExecutionContext
) {

  def apply(request: CrearSolicitud.Request, userId: UUID): Future[CrearSolicitud.Response] = {
    for {
      alumno <- alumnoRepository.findByUser(userId).map(_.getOrElse(throw new Exception("Alumno not found")))
      createSolicitud = repositories.models.SolicitudMovilidad.Crear(
        idSolicitud = UUID.randomUUID(),
        idAlumno = alumno.idAlumno,
        fecha = Instant.now(),
        descripcion = request.descripcion,
        idInstituto = request.idInstitutoDestino
      )
      _ <- solicitudMovilidadRepository.crear(createSolicitud)
      temp = MateriaMovilidad.Crear(idSolicitud = createSolicitud.idSolicitud, idMateria = request.idMateria)
      _ <- materiaMovilidadRepository.create(temp)
    } yield CrearSolicitud.Response(
      id = createSolicitud.idSolicitud,
      fecha = createSolicitud.fecha,
      descripcion = createSolicitud.descripcion
    )
  }
}
