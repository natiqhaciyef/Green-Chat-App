package com.natiqhaciyef.greenchatapp.data.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.natiqhaciyef.greenchatapp.data.roomdb.AppDatabase
import com.natiqhaciyef.greenchatapp.data.roomdb.UserDao
import com.natiqhaciyef.greenchatapp.data.source.UserDataSource
import com.natiqhaciyef.greenchatapp.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, AppDatabase::class.java, "app_database")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun provideUserDao(db: AppDatabase) = db.getUserDao()

    @Provides
    @Singleton
    fun provideUserDataSource(dao: UserDao) = UserDataSource(dao)

    @Provides
    @Singleton
    fun provideUserRepository(ds: UserDataSource) = UserRepository(ds)

}
