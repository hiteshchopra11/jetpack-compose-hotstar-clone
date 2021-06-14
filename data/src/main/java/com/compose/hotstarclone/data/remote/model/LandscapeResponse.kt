package com.compose.hotstarclone.data.remote.model

class LandscapeResponse : ArrayList<LandscapeItem>()

data class LandscapeItem(
  val id: String,
  val landscape_url: String,
  val name: String
)