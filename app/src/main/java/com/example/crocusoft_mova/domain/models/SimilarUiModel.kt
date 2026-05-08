package com.example.crocusoft_mova.domain.models

data class SimilarUiModel(
    val id: Int,
    val title: String,
    val poster_path: String,
    val backdrop_path: String,
    val vote_average: Double,
    val release_date: String,
    val overview: String
){
    companion object{
        val mock = SimilarUiModel(
            id = 0,
            title = "",
            poster_path = "",
            backdrop_path = "",
            vote_average = 0.0,
            release_date = "",
            overview = ""
        )
    }

}