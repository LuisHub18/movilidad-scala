package net.wiringbits.components.pages

import net.wiringbits.AppContext
import slinky.core.FunctionalComponent
import slinky.core.annotations.react
import slinky.web.html._

@react object SolicitarMovilidadPage {
  case class Props(ctx: AppContext)

  val component: FunctionalComponent[Props] = FunctionalComponent[Props] { props =>
    p("Solicitar Movilidad Page")
  }
}
