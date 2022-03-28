package com.pajaga.model

data class NewNewsModel (
    var status : String? = "",
    var totalResults : Int? = 0,
    var articles: ArrayList<Articles>? = null
)
