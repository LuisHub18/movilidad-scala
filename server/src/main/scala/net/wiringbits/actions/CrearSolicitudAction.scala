package net.wiringbits.actions

import net.wiringbits.api.models.{CrearSolicitud, CreateUser}
import net.wiringbits.apis.ReCaptchaApi
import net.wiringbits.config.UserTokensConfig
import net.wiringbits.repositories
import net.wiringbits.repositories.{SolicitudMovilidadRepository, UsersRepository}
import net.wiringbits.repositories.models.User
import net.wiringbits.util.{EmailsHelper, TokenGenerator, TokensHelper}
import net.wiringbits.validations.{ValidateCaptcha, ValidateEmailIsAvailable}
import org.mindrot.jbcrypt.BCrypt

import java.time.Instant
import java.util.UUID
import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

class CrearSolicitudAction @Inject() (
 solicitudMovilidadRepository: SolicitudMovilidadRepository,
)(implicit
 ec: ExecutionContext
) {

  def apply(request: CrearSolicitud.Request): Future[CrearSolicitud.Response] = {
    val createSolicitud = repositories.models.SolicitudMovilidad.Crear(
      idSolicitud = UUID.randomUUID(),
      idAlumno = request.idAlumno,
      fecha = request.fecha,
      descripcion = request.descripcion,
      idInstituto = request.idInstituto
    )

    solicitudMovilidadRepository.crear(createSolicitud).map { _ =>
      CrearSolicitud.Response(
        id = createSolicitud.idSolicitud,
        fecha = createSolicitud.fecha,
        descripcion = createSolicitud.descripcion
      )
    }
  }
}
