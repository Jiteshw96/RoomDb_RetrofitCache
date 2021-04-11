package com.mindorks.bootcamp.learndagger.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import com.mindorks.bootcamp.learndagger.dao.SchoolDao

import com.mindorks.bootcamp.learndagger.di.ApplicationContext
import com.mindorks.bootcamp.learndagger.di.DatabaseInfo
import com.mindorks.bootcamp.learndagger.entities.Director
import com.mindorks.bootcamp.learndagger.entities.School
import com.mindorks.bootcamp.learndagger.entities.Student
import com.mindorks.bootcamp.learndagger.entities.Subject
import com.mindorks.bootcamp.learndagger.entities.relations.StudentSubjectCrossRef

import javax.inject.Inject
import javax.inject.Singleton

/**
 * Dummy class to simulate the actual Database using Room or Realm etc
 */
@Singleton
@Database(
        entities = [
            School::class,
            Student::class,
            Director::class,
            Subject::class,
            StudentSubjectCrossRef::class
        ],
        exportSchema = false,
        version = 2
)
abstract class DatabaseService : RoomDatabase() {

    abstract  fun schoolDao():SchoolDao

}
