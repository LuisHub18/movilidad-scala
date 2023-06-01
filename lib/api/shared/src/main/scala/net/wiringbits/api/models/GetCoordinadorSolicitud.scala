package net.wiringbits.api.models

import play.api.libs.json.{Format, Json}

import java.time.Instant
import java.util.UUID

object GetCoordinadorSolicitud {
  case class Response(date: List[Response.SolicitudMovilidad])

  object Response {
    case class SolicitudMovilidad(
        idSolicitudMovilidad: UUID,
        idAlumno: UUID,
        fecha: Instant,
        descripcion: String,
        instituto: String
    )

    implicit val solicitudMovilidadFormat: Format[SolicitudMovilidad] = Json.format[SolicitudMovilidad]
  }

  implicit val responseFormat: Format[Response] = Json.format[Response]
}
