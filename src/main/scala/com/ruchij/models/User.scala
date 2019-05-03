package com.ruchij.models

import java.util.UUID

case class User(id: UUID, username: String, firstName: String, lastName: String, email: String)
