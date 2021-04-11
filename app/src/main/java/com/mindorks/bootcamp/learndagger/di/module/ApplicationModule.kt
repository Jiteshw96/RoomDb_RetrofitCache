package com.mindorks.bootcamp.learndagger.di.module

import android.content.Context
import androidx.room.Room
import com.mindorks.bootcamp.learndagger.BuildConfig

import com.mindorks.bootcamp.learndagger.MyApplication
import com.mindorks.bootcamp.learndagger.data.local.DatabaseService
import com.mindorks.bootcamp.learndagger.data.local.MIGRATION_1_2
import com.mindorks.bootcamp.learndagger.data.remote.NetworkService
import com.mindorks.bootcamp.learndagger.data.remote.Networking
import com.mindorks.bootcamp.learndagger.data.remote.Networking.API_KEY
import com.mindorks.bootcamp.learndagger.di.ApplicationContext
import com.mindorks.bootcamp.learndagger.di.DatabaseInfo
import com.mindorks.bootcamp.learndagger.di.NetworkInfo

import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: MyApplication) {

    @ApplicationContext
    @Provides
    fun provideContext(): Context = application

    @Provides
    @DatabaseInfo
    fun provideDatabaseName(): String = "dummy_db"

    @Provides
    @DatabaseInfo
    fun provideDatabaseVersion(): Int = 1

    @Provides
    @NetworkInfo
    fun provideApiKey(): String = "SOME_API_KEY"

    @Provides
    @Singleton
    fun getDatabaseService():DatabaseService = Room.databaseBuilder(
            application,
            DatabaseService::class.java,
            "school-database-project"
    ).addMigrations(MIGRATION_1_2).build()


    @Provides
    fun provideCompositeDisposable():CompositeDisposable = CompositeDisposable()


    @Provides
    @Singleton
    fun provideNetworkService():NetworkService{
       return Networking.create(
                BuildConfig.API_KEY,
                BuildConfig.BASE_URL,
                application.cacheDir,
                10*1024*1024 //10MB
        )
    }
}
