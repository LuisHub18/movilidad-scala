package net.wiringbits.repositories

import anorm._
import anorm.postgresql._
import net.wiringbits.common.models.{Email, Name}
import net.wiringbits.models.jobs.{BackgroundJobStatus, BackgroundJobType}
import net.wiringbits.repositories.models._

package object daos {

  import anorm.{Column, MetaDataItem, TypeDoesNotMatch}
  import org.postgresql.util.PGobject

  implicit val citextToString: Column[String] = Column.nonNull { case (value, meta) =>
    val MetaDataItem(qualified, _, clazz) = meta
    value match {
      case str: String => Right(str)
      case obj: PGobject if "citext" equalsIgnoreCase obj.getType => Right(obj.getValue)
      case _ =>
        Left(
          TypeDoesNotMatch(
            s"Cannot convert $value: ${value.asInstanceOf[AnyRef].getClass} to String for column $qualified, class = $clazz"
          )
        )
    }
  }

  implicit val nameParser: Column[Name] = Column.columnToString.map(Name.trusted)
  implicit val emailParser: Column[Email] = citextToString.map(Email.trusted)

  val userParser: RowParser[User] = {
    Macro.parser[User](
      "user_id",
      "name",
      "email",
      "password",
      "created_at",
      "verified_on",
      "id_carrera",
      "id_rol",
      "id_instituto"
    )
  }

  val materiaParser: RowParser[Materia] = {
    Macro.parser[Materia](
      "id_materia",
      "nombre"
    )
  }

  val userLogParser: RowParser[UserLog] = {
    Macro.parser[UserLog]("user_log_id", "user_id", "message", "created_at")
  }

  def enumColumn[A](f: String => Option[A]): Column[A] = Column.columnToString.mapResult { string =>
    f(string)
      .toRight(SqlRequestError(new RuntimeException(s"The value $string doesn't exists")))
  }

  implicit val tokenTypeColumn: Column[UserTokenType] = enumColumn(
    UserTokenType.withNameInsensitiveOption
  )

  implicit val tokenParser: RowParser[UserToken] = {
    Macro.parser[UserToken]("user_token_id", "token", "token_type", "created_at", "expires_at", "user_id")
  }

  implicit val backgroundJobStatusColumn: Column[BackgroundJobStatus] = enumColumn(
    BackgroundJobStatus.withNameInsensitiveOption
  )

  implicit val backgroundJobTypeColumn: Column[BackgroundJobType] = enumColumn(
    BackgroundJobType.withNameInsensitiveOption
  )

  implicit val backgroundJobParser: RowParser[BackgroundJobData] = {
    Macro.parser[BackgroundJobData](
      "background_job_id",
      "type",
      "payload",
      "status",
      "status_details",
      "error_count",
      "execute_at",
      "created_at",
      "updated_at"
    )
  }

  implicit val estatusParser: RowParser[Estatus] = {
    Macro.parser[Estatus](
      "id_estatus",
      "descripcion"
    )
  }

  implicit val alumnoParser: RowParser[Alumno] = {
    Macro.parser[Alumno](
      "id_alumno",
      "semestre",
      "num_movilidades",
      "deuda",
      "user_id"
    )
  }

  implicit val institutoParser: RowParser[Instituto] = {
    Macro.parser[Instituto](
      "id_instituto",
      "nombre",
      "domicilio",
      "telefono"
    )
  }

  val carreraParser: RowParser[Carrera] = {
    Macro.parser[Carrera](
      "id_carrera",
      "nombre"
    )
  }

  val rolParser: RowParser[Rol] = {
    Macro.parser[Rol](
      "id_rol",
      "tipo"
    )
  }

  implicit val solicitudMovilidadParser: RowParser[SolicitudMovilidad] = {
    Macro.parser[SolicitudMovilidad](
      "id_solicitud",
      "id_alumno",
      "fecha",
      "descripcion",
      "id_instituto"
    )
  }

  implicit val carreraInstitutoParser: RowParser[CarreraInstituto] = {
    Macro.parser[CarreraInstituto](
      "id_carrera",
      "id_instituto"
    )
  }

  implicit val materiaCarreraParser: RowParser[MateriaCarrera] = {
    Macro.parser[MateriaCarrera](
      "id_carrera",
      "id_materia"
    )
  }

  implicit val materiaMovilidadParser: RowParser[MateriaMovilidad] = {
    Macro.parser[MateriaMovilidad](
      "id_solicitud",
      "id_materia",
      "calificacion"
    )
  }

  implicit val movimientosParser: RowParser[Movimientos] = {
    Macro.parser[Movimientos](
      "id_solicitud_movilidad",
      "user_id",
      "fecha_movimiento",
      "id_estatus"
    )
  }
}
