package com.pajaga.model

data class NewsModel(
    var status : String? = "",
    var totalResults : Int? = 0,
    var results: ArrayList<News>? = null
)
