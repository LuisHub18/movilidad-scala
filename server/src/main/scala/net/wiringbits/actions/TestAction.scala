package net.wiringbits.actions

import net.wiringbits.api.models.TestModel
import net.wiringbits.repositories.EstatusRepository

import java.util.UUID
import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

class TestAction @Inject() (
    estatusRepository: EstatusRepository
)(implicit ec: ExecutionContext) {
  def apply(idEstatus: UUID): Future[TestModel.Response] = {
    for {
      temp <- estatusRepository.find(idEstatus).map(_.getOrElse(throw new Exception("No se encontro el estatus")))
    } yield TestModel.Response(idStatus = temp.idEstatus, descripcion = temp.descripcion)
  }
}
