package com.example.crocusoft_mova.core.di

import com.example.crocusoft_mova.domain.repository.SignUpRepository
import com.example.crocusoft_mova.domain.usecases.SignUpUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideSignUpUseCase(repository: SignUpRepository): SignUpUseCase {
        return SignUpUseCase(repository)
    }

}