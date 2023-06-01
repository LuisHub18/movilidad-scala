package net.wiringbits.components.pages

import net.wiringbits.AppContext
import slinky.core.FunctionalComponent
import slinky.core.annotations.react
import slinky.web.html.p
import com.alexitc.materialui.facade.materialUiCore.{components => mui, materialUiCoreStrings => muiStrings}
import net.wiringbits.api.models.GetSolicitudesAlumno
import org.scalajs.macrotaskexecutor.MacrotaskExecutor.Implicits.global
import slinky.core.facade.Hooks

import java.util.UUID

@react object ConsultarSolicitudesPage {
  case class Props(ctx: AppContext)

  val component: FunctionalComponent[Props] = FunctionalComponent[Props] { props =>
    val (solicitudes, setSolicitudes) =
      Hooks.useState[List[GetSolicitudesAlumno.Response.SolicitudMovilidad]](List.empty)

    Hooks.useEffect(
      () => {
        props.ctx.api.client.getSolicitudesAlumno().onComplete {
          case scala.util.Success(solicitudes) =>
            setSolicitudes(solicitudes.date)
          case scala.util.Failure(exception) =>
            println(exception)
        }
      },
      ""
    )

//    def instituto(id: UUID): String = {
//      props.ctx.api.client.getInstituto(id).map(_.nombre).getOrElse("Instituto desconocido")
//    }

    mui.Table(
      mui.TableHead(
        mui.TableRow(
          mui.TableCell("Folio"),
          mui.TableCell("Instituto"),
          mui.TableCell("Fecha"),
          mui.TableCell("Descripción")
        )
      ),
      mui.TableBody(
        solicitudes.map { solicitud =>
          mui
            .TableRow(
              mui.TableCell(solicitud.idSolicitudMovilidad.toString),
              mui.TableCell(solicitud.instituto),
              mui.TableCell(solicitud.fecha.toString),
              mui.TableCell(solicitud.descripcion)
            )
            .withKey(solicitud.idSolicitudMovilidad.toString)
        }
      )
    )
  }
}
