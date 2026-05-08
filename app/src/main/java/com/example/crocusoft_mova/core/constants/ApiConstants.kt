package com.example.crocusoft_mova.core.constants

object ApiConstants {

    const val BASE_URL = "https://api.themoviedb.org/3"

    const val DISCOVER = "$BASE_URL/discover/{category}"

    const val NOW_PLAYING = "$BASE_URL/movie/now_playing"

    const val TRENDING = "$BASE_URL/trending/movie/day"

    const val REGIONS = "$BASE_URL/configuration/countries"

    const val GENRES_MOVIE = "$BASE_URL/genre/movie/list"

    const val GENRES_TV = "$BASE_URL/genre/tv/list"

    const val MOVIE_DETAIL = "$BASE_URL/movie/{movie_id}"

    const val MOVIE_VIDEOS =  "$BASE_URL/movie/{movie_id}/videos"

    const val MOVIE_SIMILAR =  "$BASE_URL/movie/{movie_id}/similar"

    const val UPCOMING  = "$BASE_URL/movie/upcoming"

    const val TOP_RATED = "$BASE_URL/movie/top_rated"

    const val SEARCH = "$BASE_URL/search/movie"

    const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/"
    
    const val POSTER_SIZE_W500 = "w500"


    fun getPosterUrl(path: String?): String {
        if (path.isNullOrEmpty()) return ""
        return "$IMAGE_BASE_URL$POSTER_SIZE_W500$path"
    }
}
