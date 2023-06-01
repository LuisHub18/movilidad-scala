package net.wiringbits.actions

import net.wiringbits.api.models.GetMaterias
import net.wiringbits.repositories.{
  CarreraInstitutoRepository,
  MateriaCarreraRepository,
  MateriaRepository,
  UsersRepository
}

import java.util.UUID
import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

class GetMateriasAction @Inject() (
    materiaRepository: MateriaRepository,
    carreraInstitutoRepository: CarreraInstitutoRepository,
    materiaCarreraRepository: MateriaCarreraRepository,
    usersRepository: UsersRepository
)(implicit ec: ExecutionContext) {
  def apply(userId: UUID, idInstituto: UUID): Future[GetMaterias.Response] = for {
    carreras <- carreraInstitutoRepository.findByInstituto(idInstituto).map(_.map(_.idCarrera))
    materias1 <- Future.sequence(
      carreras.map(x => materiaCarreraRepository.findByCarrera(x).map(_.filter(_.idCarrera == x).map(_.idMateria)))
    )
    materias2 = materias1.flatten.distinct
    materiasResult <- materiaRepository.all().map(_.filter(x => materias2.contains(x.id_materia)))
  } yield GetMaterias.Response(
    materiasResult.map(x => GetMaterias.Response.Materia(x.id_materia, x.nombre))
  )
}
