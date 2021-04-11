package com.mindorks.bootcamp.learndagger.di.module

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner

import com.mindorks.bootcamp.learndagger.di.ActivityContext
import com.mindorks.bootcamp.learndagger.di.ActivityScope
import dagger.Binds

import dagger.Module
import dagger.Provides
import java.security.acl.Owner

@Module
class ActivityModule(private val activity: AppCompatActivity) {

    @ActivityContext
    @Provides
    fun provideContext(): Context = activity

    @ActivityScope
    @Provides
    fun getOwner(): LifecycleOwner {
        return activity
    }
}
