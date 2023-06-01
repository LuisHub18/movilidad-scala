package net.wiringbits.api.models

import net.wiringbits.api.models.SendEmailVerificationToken.Request
import play.api.libs.json.{Format, Json}

import java.util.UUID

object TestModel {
  case class Response(idStatus: UUID, descripcion: String)

  implicit val testModelRequestFormat: Format[Request] = Json.format[Request]
}
