package com.mindorks.bootcamp.learndagger.entities.relations

import androidx.room.Entity

/* N to M is different than one to M so here in data class we have to define the entity and data how tables are connected
* This cross Ref will contain primary key from both the table and it doesn't have its own primary key
*/
@Entity(primaryKeys = ["studentName","subjectName"])
data class StudentSubjectCrossRef(

        val studentName:String,

        val subjectName:String

)
