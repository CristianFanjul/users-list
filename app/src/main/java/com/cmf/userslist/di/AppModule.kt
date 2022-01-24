package com.cmf.userslist.di

import com.cmf.userslist.domain.datasource.UsersDataSource
import com.cmf.userslist.domain.datasource.UsersDataSourceImpl
import com.cmf.userslist.domain.repository.UsersRepository
import com.cmf.userslist.domain.repository.UsersRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Singleton
    @Binds
    abstract fun bindUsersRepository(repository: UsersRepositoryImpl): UsersRepository

    @Singleton
    @Binds
    abstract fun bindUsersDataSource(dataSource: UsersDataSourceImpl): UsersDataSource
}