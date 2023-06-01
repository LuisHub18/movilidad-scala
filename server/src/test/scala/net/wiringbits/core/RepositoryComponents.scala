package net.wiringbits.core

import net.wiringbits.repositories._
import play.api.db.Database

case class RepositoryComponents(
    database: Database,
    users: UsersRepository,
    userTokens: UserTokensRepository,
    userLogs: UserLogsRepository,
    backgroundJobs: BackgroundJobsRepository,
    estatusRepository: EstatusRepository,
    alumnoRepository: AlumnoRepository,
    materia: MateriaRepository,
    institutoRepository: InstitutoRepository,
    carreraRepository: CarreraRepository,
    rolRepository: RolRepository,
    movimientoRepository: SolicitudMovilidadRepository
)
