package net.wiringbits.actions

import net.wiringbits.api.models.SolicitudCoordinador
import net.wiringbits.repositories.models.Movimientos
import net.wiringbits.repositories.{EstatusRepository, MovimientosRepository}

import java.time.Instant
import java.util.UUID
import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

class SolicitudCoordinadorAction @Inject() (
    movimientosRepository: MovimientosRepository,
    estatusRepository: EstatusRepository
)(implicit ec: ExecutionContext) {
  def apply(solicitud: UUID, userId: UUID, tipo: String): Future[SolicitudCoordinador.Response] = {
    for {
      todos <- estatusRepository
        .all()
        .map(_.find(_.descripcion == tipo).getOrElse(throw new Exception("No se encontro el estatus Todos")))
      _ <- movimientosRepository.create(
        Movimientos.Crear(
          idSolicitudMovilidad = solicitud,
          idUsuario = userId,
          fechaMovimiento = Instant.now(),
          idEstatus = todos.idEstatus
        )
      )
    } yield SolicitudCoordinador.Response()
  }
}
