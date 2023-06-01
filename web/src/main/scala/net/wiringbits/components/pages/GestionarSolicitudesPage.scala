package net.wiringbits.components.pages

import net.wiringbits.AppContext
import net.wiringbits.api.models.GetCoordinadorSolicitud
import org.scalajs.macrotaskexecutor.MacrotaskExecutor.Implicits.global
import slinky.core.FunctionalComponent
import slinky.core.annotations.react
import com.alexitc.materialui.facade.materialUiCore.{components => mui, materialUiCoreStrings => muiStrings}
import net.wiringbits.webapp.utils.slinkyUtils.core.GenericHooks
import slinky.core.facade.{Fragment, Hooks}

@react object GestionarSolicitudesPage {
  case class Props(ctx: AppContext)

  val component: FunctionalComponent[Props] = FunctionalComponent[Props] { props =>
    val (solicitudes, setSolicitudes) =
      Hooks.useState[List[GetCoordinadorSolicitud.Response.SolicitudMovilidad]](List.empty)
    val (_, update) = GenericHooks.useForceRefresh

    Hooks.useEffect(
      () => {
        props.ctx.api.client.getCoordinadorSolicitud().onComplete {
          case scala.util.Success(value) => {
            setSolicitudes(value.date)
          }
          case scala.util.Failure(exception) => {
            println(exception)
          }
        }
      },
      ""
    )

    mui.Table(
      mui.TableHead(
        mui.TableRow(
          mui.TableCell("Folio"),
          mui.TableCell("Alumno"),
          mui.TableCell("Fecha"),
          mui.TableCell("Descripcion"),
          mui.TableCell("Acciones")
        )
      ),
      mui.TableBody(
        solicitudes.map { solicitud =>
          mui.TableRow(
            mui.TableCell(solicitud.idSolicitudMovilidad.toString),
            mui.TableCell(solicitud.idAlumno.toString),
            mui.TableCell(solicitud.fecha.toString),
            mui.TableCell(solicitud.descripcion),
            mui.TableCell(
              mui
                .Button("Aceptar")
                .onClick(_ => {
                  props.ctx.api.client.solicitudCoordinador(solicitud.idSolicitudMovilidad, "Aceptar").onComplete {
                    case scala.util.Success(value) => {
                      update()
                    }
                    case scala.util.Failure(exception) => {
                      println(exception)
                    }
                  }
                }),
              mui
                .Button("Denegar")
                .onClick(_ => {
                  props.ctx.api.client.solicitudCoordinador(solicitud.idSolicitudMovilidad, "Rechazar").onComplete {
                    case scala.util.Success(value) => {
                      update()
                    }
                    case scala.util.Failure(exception) => {
                      println(exception)
                    }
                  }
                })
            )
          )
        }
      )
    )
  }
}
