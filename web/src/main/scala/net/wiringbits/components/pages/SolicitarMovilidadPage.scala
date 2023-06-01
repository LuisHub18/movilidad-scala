package net.wiringbits.components.pages

import net.wiringbits.AppContext
import net.wiringbits.api.models.{GetMaterias, GetUniversidades}
import org.scalajs.macrotaskexecutor.MacrotaskExecutor.Implicits.global
import com.alexitc.materialui.facade.materialUiCore.{components => mui, materialUiCoreStrings => muiStrings}
import net.wiringbits.models.User
import net.wiringbits.webapp.utils.slinkyUtils.components.core.widgets.Container
import net.wiringbits.webapp.utils.slinkyUtils.components.core.widgets.Container.Alignment
import slinky.core.FunctionalComponent
import slinky.core.annotations.react
import slinky.core.facade.{Fragment, Hooks}
import slinky.web.html._

import java.util.UUID

@react object SolicitarMovilidadPage {
  case class Props(ctx: AppContext, user: User)

  val component: FunctionalComponent[Props] = FunctionalComponent[Props] { props =>
    val (universidades, setUniversidades) = Hooks.useState[List[GetUniversidades.Response.Universidad]](List.empty)
    val (materias, setMaterias) = Hooks.useState[List[GetMaterias.Response.Materia]](List.empty)
    val (universidad, setUniversidad) = Hooks.useState[String]("")
    val (materia, setMateria) = Hooks.useState[String]("")

    Hooks.useEffect(
      () => {
        props.ctx.api.client.getUniversidades().onComplete {
          case scala.util.Success(universidades) =>
            setUniversidades(universidades.data.filterNot(_.id_instituto != props.user.id_instituto))
          case scala.util.Failure(exception) =>
            println(exception)
        }
      },
      ""
    )

    Hooks.useEffect(
      () => {
        if (universidad.nonEmpty) {
          props.ctx.api.client.getMaterias(UUID.fromString(universidad)).onComplete {
            case scala.util.Success(materias) =>
              setMaterias(materias.data)
            case scala.util.Failure(exception) =>
              println(exception)
          }
        }
      },
      List(universidad)
    )

    val institutos = mui.FormControl(
      mui.InputLabel()("Universidad"),
      mui
        .Select()(
          universidades.map(universidad =>
            mui
              .MenuItem()(
                mui.Typography(universidad.nombre)
              )
              .value(universidad.id_instituto.toString)
          )
        )
        .value(universidad)
        .onChange((e, _) => setUniversidad(e.target_ChangeEvent.value))
    )

    val materiasForm = mui.FormControl(
      mui.InputLabel()("Materias"),
      mui
        .Select()(
          materias.map { materia =>
            mui
              .MenuItem()(
                mui.Typography(materia.nombre)
              )
              .value(materia.idMateria.toString)
          }
        )
        .value(materia)
        .onChange((e, _) => setMateria(e.target_ChangeEvent.value))
        .disabled(materias.isEmpty)
    )

    def handleSubmit = {}

    div(
      Container(
        minWidth = Some("50%"),
        maxWidth = Some("50%"),
        flex = Some(1),
        alignItems = Container.Alignment.center,
        justifyContent = Container.Alignment.center,
        child = Fragment(
          mui.Typography("Solicitar movilidad"),
          institutos.fullWidth(true),
          materiasForm.fullWidth(true),
          mui
            .Button("Solicitar")
            .onClick(_ => handleSubmit)
            .disabled(materia.isEmpty)
        )
      )
    )
  }
}
