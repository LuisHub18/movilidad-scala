package net.wiringbits.models

import net.wiringbits.common.models.{Email, Name}

import java.util.UUID

case class User(name: Name, email: Email, rol: String, id_instituto: UUID)
