package com.example.crocusoft_mova.domain.models

data class MovieDetailUiModel(
    val id: Int,
    val title: String,
    val release_date: String,
    val description: String,
    val vote_average: Double,
    val image: String,
    val genres: List<String>,
    val production_companies: List<ProductionCompany>,
    val language: String,
    val mediaType : String


    ) {

    companion object {
        val empty = MovieDetailUiModel(
            id = 0,
            title = "",
            release_date = "",
            description = "",
            vote_average = 0.0,
            image = "",
            genres = emptyList(),
            production_companies = emptyList(),
            language = "",
            mediaType = ""
        )

    }
}

data class ProductionCompany(
    val name: String,
    val logo_path: String,
)


