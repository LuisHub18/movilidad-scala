package net.wiringbits.actions

import net.wiringbits.api.models.{GetUniversidades, GetUserLogs}
import net.wiringbits.repositories._

import java.util.UUID
import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

class GetUniversidadesAction @Inject() (
  universidadesRepository: InstitutoRepository
)(implicit ec: ExecutionContext) {

  def apply(): Future[GetUniversidades.Response] = {
    for {
      universidades <- universidadesRepository.all()
      items = universidades.map { x =>
        GetUniversidades.Response.Universidad(
          id_instituto = x.idInstituto,
          nombre = x.nombre,
          domicilio = x.domicilio,
          telefono = x.telefono
        )
      }
    } yield GetUniversidades.Response(items)
  }
}
