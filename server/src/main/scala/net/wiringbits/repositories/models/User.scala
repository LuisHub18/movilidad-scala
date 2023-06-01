package net.wiringbits.repositories.models

import net.wiringbits.common.models.{Email, Name}

import java.time.Instant
import java.util.UUID

case class User(
    id: UUID,
    name: Name,
    email: Email,
    hashedPassword: String,
    createdAt: Instant,
    verifiedOn: Option[Instant],
    idCarrera: UUID,
    idRol: UUID,
    idInstituto: UUID
)

object User {
  case class CreateUser(
      id: UUID,
      name: Name,
      email: Email,
      hashedPassword: String,
      verifyEmailToken: String,
      idCarrera: UUID = UUID.randomUUID(),
      idRol: UUID = UUID.randomUUID(),
      idInstituto: UUID = UUID.randomUUID()
  )
}
