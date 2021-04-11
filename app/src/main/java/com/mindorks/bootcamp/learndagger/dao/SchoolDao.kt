package com.mindorks.bootcamp.learndagger.dao

import androidx.room.*
import com.mindorks.bootcamp.learndagger.entities.Director
import com.mindorks.bootcamp.learndagger.entities.School
import com.mindorks.bootcamp.learndagger.entities.Student
import com.mindorks.bootcamp.learndagger.entities.Subject
import com.mindorks.bootcamp.learndagger.entities.relations.*
import io.reactivex.Completable
import io.reactivex.Single

//Created only Single Dao for School Database
@Dao
interface SchoolDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSchool(school: School):Completable

    @Transaction
    @Query("SELECT * FROM school WHERE schoolName = :schoolName")
    fun getSchoolsAndDirectorsWithSchoolName(schoolName:String):Single<List<SchoolsAndDirectors>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDirector(director: Director):Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertStudents(student: Student):Completable


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMany(list: List<Student>):Completable

    @Transaction
    @Query("SELECT * FROM school WHERE schoolName = :schoolName")
    fun getSchoolWithStudents(schoolName:String):Single<List<SchoolWithStudents>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSubject(subject:Subject):Completable

    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    fun insertStudentSubjectCrossRef(crossRef:StudentSubjectCrossRef):Completable


    @Transaction
    @Query("SELECT * FROM subject WHERE subjectName = :subjectName")
    fun getAllStudentOfSubject(subjectName:String):Single<List<SubjectWithStudents>>


    @Transaction
    @Query("SELECT * FROM student WHERE studentName = :studentName")
    fun getAllSubjectsOfStudent(studentName:String):List<StudentWithSubjects>


}