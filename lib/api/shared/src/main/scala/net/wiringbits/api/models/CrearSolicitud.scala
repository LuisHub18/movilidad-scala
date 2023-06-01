package net.wiringbits.api.models

import io.swagger.annotations._
import net.wiringbits.common.models.{Captcha, Email, Name, Password}
import play.api.libs.json.{Format, Json}

import java.time.Instant
import java.util.UUID

object CrearSolicitud {
  @ApiModel(value = "CrearSolicitudRequest", description = "Request for the create solicitud API")
  case class Request(
      idMateria: UUID,
      @ApiModelProperty(value = "The description of the solicitud", dataType = "String", example = "notSoWeakPassword")
      descripcion: String,
      @ApiModelProperty(value = "Instituto", dataType = "String")
      idInstitutoDestino: UUID
  )
  @ApiModel(value = "CreateUserResponse", description = "Response for the create user API")
  case class Response(
      @ApiModelProperty(
        value = "The id for the created solicitud",
        dataType = "String",
        example = "e9e8d358-b989-4dd1-834d-764cac539fb1"
      )
      id: UUID,
      @ApiModelProperty(value = "The date for the solicitud", dataType = "String", example = "2023-07-12")
      fecha: Instant,
      @ApiModelProperty(value = "The description of the solicitud", dataType = "String", example = "Alex")
      descripcion: String
  )

  implicit val createSolicitudRequestFormat: Format[Request] = Json.format[Request]
  implicit val createSolicitudResponseFormat: Format[Response] = Json.format[Response]
}
