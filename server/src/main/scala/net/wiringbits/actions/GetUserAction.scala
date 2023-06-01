package net.wiringbits.actions

import net.wiringbits.api.models.GetCurrentUser
import net.wiringbits.repositories.{RolRepository, UsersRepository}
import net.wiringbits.repositories.models.User

import java.util.UUID
import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

class GetUserAction @Inject() (
    usersRepository: UsersRepository,
    rolRepository: RolRepository
)(implicit ec: ExecutionContext) {

  def apply(userId: UUID): Future[GetCurrentUser.Response] = {
    for {
      user <- unsafeUser(userId)
      rol <- rolRepository
        .find(user.idRol)
        .map(_.getOrElse(throw new RuntimeException(s"Unexpected error because the rol wasn't found")))
    } yield GetCurrentUser.Response(
      id = user.id,
      email = user.email,
      name = user.name,
      createdAt = user.createdAt,
      rol = rol.tipo,
      idInstituto = user.idInstituto
    )
  }

  private def unsafeUser(userId: UUID): Future[User] = {
    usersRepository
      .find(userId)
      .map { maybe =>
        maybe.getOrElse(
          throw new RuntimeException(
            s"Unexpected error because the user wasn't found: $userId"
          )
        )
      }
  }
}
