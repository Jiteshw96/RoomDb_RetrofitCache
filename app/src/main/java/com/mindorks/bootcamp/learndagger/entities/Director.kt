package com.mindorks.bootcamp.learndagger.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "director")
data class Director(
        @PrimaryKey(autoGenerate = false)
        val directorName:String,
        //School Name is just to store which school is the director in so helpful for room to Join these two table
        val schoolName:String
        )