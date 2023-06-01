package net.wiringbits.api.models

import play.api.libs.json.{Format, Json}

object SolicitudCoordinador {
  case class Response(noData: String = "")

  implicit val response: Format[Response] = Json.format[Response]
}
