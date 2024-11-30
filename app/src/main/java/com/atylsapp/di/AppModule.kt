package com.atylsapp.di

import com.atylsapp.repository.MovieRepo
import com.atylsapp.repository.MovieRepoImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface MovieModule {

    @Binds
    fun bindsMovieRepo(impl: MovieRepoImpl): MovieRepo
}