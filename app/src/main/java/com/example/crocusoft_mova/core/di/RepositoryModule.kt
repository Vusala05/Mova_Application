package com.example.crocusoft_mova.core.di

import com.example.crocusoft_mova.data.repository.BookmarkRepositoryImpl
import com.example.crocusoft_mova.data.repository.ChooseInterestRepositoryImpl
import com.example.crocusoft_mova.data.repository.ExploreRepositoryImpl
import com.example.crocusoft_mova.data.repository.FillProfileRepositoryImpl
import com.example.crocusoft_mova.data.repository.HomeRepositoryImpl
import com.example.crocusoft_mova.data.repository.MovieDetailRepositoryImpl
import com.example.crocusoft_mova.data.repository.PinRepositoryImpl
import com.example.crocusoft_mova.data.repository.ProfileRepositoryImpl
import com.example.crocusoft_mova.data.repository.SignInRepositoryImpl
import com.example.crocusoft_mova.data.repository.SignUpRepositoryImpl
import com.example.crocusoft_mova.data.service.remote.ApiService
import com.example.crocusoft_mova.domain.repository.BookmarkRepository
import com.example.crocusoft_mova.domain.repository.ChooseInterestRepository
import com.example.crocusoft_mova.domain.repository.ExploreRepository
import com.example.crocusoft_mova.domain.repository.FillProfileRepository
import com.example.crocusoft_mova.domain.repository.HomeRepository
import com.example.crocusoft_mova.domain.repository.MovieDetailRepository
import com.example.crocusoft_mova.domain.repository.PinRepository
import com.example.crocusoft_mova.domain.repository.ProfileRepository
import com.example.crocusoft_mova.domain.repository.SignInRepository
import com.example.crocusoft_mova.domain.repository.SignUpRepository
import com.example.crocusoft_mova.domain.usecases.FetchMovieDetailUseCase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideSignUpRepository(firebaseAuth: FirebaseAuth): SignUpRepository =
        SignUpRepositoryImpl(firebaseAuth)

    @Singleton
    @Provides
    fun provideSignInRepository(
        firebaseAuth: FirebaseAuth,
        firestore: FirebaseFirestore
    ): SignInRepository =
        SignInRepositoryImpl(firebaseAuth, firestore)

    @Singleton
    @Provides
    fun provideChooseInterestRepository(
        firebaseAuth: FirebaseAuth,
        firestore: FirebaseFirestore
    ): ChooseInterestRepository =
        ChooseInterestRepositoryImpl(firebaseAuth, firestore)


    @Singleton
    @Provides
    fun provideFillProfileRepository(
        firebaseAuth: FirebaseAuth,
        firestore: FirebaseFirestore
    ): FillProfileRepository =
        FillProfileRepositoryImpl(firebaseAuth, firestore)

    @Singleton
    @Provides
    fun providePinRepository(
        firebaseAuth: FirebaseAuth,
        firestore: FirebaseFirestore
    ): PinRepository = PinRepositoryImpl(firebaseAuth, firestore)

    @Singleton
    @Provides
    fun provideBookmarkRepository(
        firebaseAuth: FirebaseAuth,
        firebaseFirestore: FirebaseFirestore,
        fetchMovieDetailUseCase: FetchMovieDetailUseCase
    ) : BookmarkRepository = BookmarkRepositoryImpl(firebaseAuth,firebaseFirestore, fetchMovieDetailUseCase)


    @Singleton
    @Provides
    fun provideHomeRepository(apiService: ApiService): HomeRepository =
        HomeRepositoryImpl(apiService)

    @Singleton
    @Provides
    fun provideProfileRepository(
        firebaseAuth: FirebaseAuth,
        firebaseFirestore: FirebaseFirestore): ProfileRepository =
        ProfileRepositoryImpl(firebaseAuth,firebaseFirestore)


    @Singleton
    @Provides
    fun provideExploreRepository(apiService: ApiService): ExploreRepository =
        ExploreRepositoryImpl(apiService)

    @Singleton
    @Provides
    fun provideMovieDetailRepository(apiService: ApiService): MovieDetailRepository =
        MovieDetailRepositoryImpl(apiService)


}