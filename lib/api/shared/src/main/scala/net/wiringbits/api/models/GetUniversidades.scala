package net.wiringbits.api.models

import io.swagger.annotations.ApiModel
import play.api.libs.json.{Format, Json}

import java.util.UUID

object GetUniversidades {
  @ApiModel(value = "GetUniversidadesResponse", description = "Incluye las universidades")
  case class Response(data: List[Response.Universidad])

  object Response {
    case class Universidad(id_instituto: UUID, nombre: String, domicilio: String, telefono: String)

    implicit val getUserLogsResponseFormat: Format[Universidad] = Json.format[Universidad]
  }

  implicit val getUserLogsResponseFormat: Format[Response] = Json.format[Response]
}
