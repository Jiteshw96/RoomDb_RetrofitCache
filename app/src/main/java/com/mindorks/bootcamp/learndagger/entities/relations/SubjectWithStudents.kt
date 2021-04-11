package com.mindorks.bootcamp.learndagger.entities.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.mindorks.bootcamp.learndagger.entities.Student
import com.mindorks.bootcamp.learndagger.entities.Subject

data class SubjectWithStudents(
        @Embedded val subject: Subject,
        @Relation(
                parentColumn = "subjectName",
                entityColumn = "studentName",
                associateBy = Junction(StudentSubjectCrossRef::class)
        )

        val studentList:List<Student>

)