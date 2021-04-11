package com.mindorks.bootcamp.learndagger.entities.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.mindorks.bootcamp.learndagger.entities.Student
import com.mindorks.bootcamp.learndagger.entities.Subject

data class StudentWithSubjects (
        @Embedded  val student:Student,

        @Relation(
                parentColumn = "studentName",
                entityColumn = "subjectName",
                associateBy = Junction(StudentSubjectCrossRef::class)
        )
        val subjectList: List<Subject>

        )