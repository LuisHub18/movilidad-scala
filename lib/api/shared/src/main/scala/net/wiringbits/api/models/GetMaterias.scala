package net.wiringbits.api.models

import play.api.libs.json.{Format, Json}

import java.util.UUID

object GetMaterias {
  case class Response(data: List[Response.Materia])

  object Response {
    case class Materia(idMateria: UUID, nombre: String)

    implicit val getMateriasResponseMateriaFormat: Format[Materia] = Json.format[Materia]
  }

  implicit val getMateriasResponseFormat: Format[Response] = Json.format[Response]
}
