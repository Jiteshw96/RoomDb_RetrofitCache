package com.mindorks.bootcamp.learndagger.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.mindorks.bootcamp.learndagger.entities.Director
import com.mindorks.bootcamp.learndagger.entities.School


data class SchoolsAndDirectors(
        @Embedded
        val school: School,
        @Relation(
                //ParentColumn checks for the column it should combine these two table
                // These two table should have a column which is used for combining
                parentColumn = "schoolName",
                entityColumn = "schoolName"
        )
        val director:Director
)