package net.wiringbits.core

import net.wiringbits.repositories._
import play.api.db.Database

case class RepositoryComponents(
    database: Database,
    users: UsersRepository,
    userTokens: UserTokensRepository,
    userLogs: UserLogsRepository,
    backgroundJobs: BackgroundJobsRepository,
<<<<<<< HEAD
    materia: MateriaRepository
=======
    estatusRepository: EstatusRepository
>>>>>>> d2ee276c7bcfe07d89e0ac845f3d637b00500c1a
)
