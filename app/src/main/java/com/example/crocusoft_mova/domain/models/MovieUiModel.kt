package com.example.crocusoft_mova.domain.models

data class MovieUiModel(
    val id:Int,
    val title:String,
    val release_date: String,
    val description: String,
    val vote_average: Double,
    val image:String,
    val type : String
)

{

    companion object{
        val mock = MovieUiModel(
            0,
            "Doctor Strange 2",
            "",
            "Action, Superhero, Science Fiction, ...",
            0.0,
            image = "https://tr.web.img2.acsta.net/pictures/22/05/05/14/02/4321698.jpg",
            type = "movie"
        )
    }
}

