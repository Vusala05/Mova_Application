package com.example.crocusoft_mova.domain.repository

import com.example.crocusoft_mova.core.ContentState

interface ChooseInterestRepository {



    suspend fun addTags(tags: List<String>): ContentState<Unit>
}