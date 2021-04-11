package com.mindorks.bootcamp.learndagger.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.mindorks.bootcamp.learndagger.entities.School
import com.mindorks.bootcamp.learndagger.entities.Student

data class SchoolWithStudents (
        @Embedded val school:School,

        @Relation(
                parentColumn = "schoolName",
                entityColumn = "schoolName"
        )

        val students:List<Student>
        )