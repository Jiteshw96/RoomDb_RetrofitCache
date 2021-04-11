package com.mindorks.bootcamp.learndagger.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "school")
data class School(
        @PrimaryKey(autoGenerate = false)
        val schoolName:String)