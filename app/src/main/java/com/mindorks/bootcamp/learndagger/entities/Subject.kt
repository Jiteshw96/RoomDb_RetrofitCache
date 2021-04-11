package com.mindorks.bootcamp.learndagger.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "subject")
data class Subject(
        @PrimaryKey(autoGenerate = false)
        val subjectName:String,

        @ColumnInfo(name= "id")
        val id:Int
 )