package net.wiringbits.repositories.daos

import net.wiringbits.repositories.models.Movimientos

import java.sql.Connection

object MovimientosDAO {
  import anorm._

  def create(request: Movimientos.Crear)(implicit conn: Connection): Unit = {
    val _ = SQL"""
      INSERT INTO movimientos
        (id_solicitud_movilidad, user_id, fecha_movimiento, id_estatus)
      VALUES (
        ${request.idSolicitudMovilidad}::UUID,
        ${request.idUsuario}::UUID,
        ${request.fechaMovimiento},
        ${request.idEstatus}::UUID
      )
      """
      .execute()
  }

  def all()(implicit conn: Connection): List[Movimientos] = {
    SQL"""
        SELECT id_solicitud_movilidad, user_id, fecha_movimiento, id_estatus
        FROM movimientos
        """.as(movimientosParser.*)
  }
}
