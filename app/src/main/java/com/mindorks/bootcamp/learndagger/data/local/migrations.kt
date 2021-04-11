package com.mindorks.bootcamp.learndagger.data.local

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase


val MIGRATION_1_2 = object: Migration(1,2){
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE subject ADD COLUMN id INTEGER NOT NULL DEFAULT 0")
    }

}