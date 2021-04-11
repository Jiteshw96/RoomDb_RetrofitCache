package com.mindorks.bootcamp.learndagger.ui.main

import android.util.Log
import androidx.lifecycle.*
import com.mindorks.bootcamp.learndagger.data.local.DatabaseService
import com.mindorks.bootcamp.learndagger.data.model.Dummy
import com.mindorks.bootcamp.learndagger.data.remote.NetworkService
import com.mindorks.bootcamp.learndagger.data.remote.request.DummyReq
import com.mindorks.bootcamp.learndagger.di.ActivityScope
import com.mindorks.bootcamp.learndagger.entities.Director
import com.mindorks.bootcamp.learndagger.entities.School
import com.mindorks.bootcamp.learndagger.entities.Student
import com.mindorks.bootcamp.learndagger.entities.Subject
import com.mindorks.bootcamp.learndagger.entities.relations.SchoolsAndDirectors
import com.mindorks.bootcamp.learndagger.entities.relations.StudentSubjectCrossRef
import com.mindorks.bootcamp.learndagger.entities.relations.SubjectWithStudents
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

import javax.inject.Inject
import javax.inject.Singleton


class MainViewModel @Inject constructor(
        private val compositeDisposable: CompositeDisposable,
        private val databaseService: DatabaseService,
        private val networkService: NetworkService,
        private val owner: LifecycleOwner):LifecycleObserver {

    val schoolData = MutableLiveData<List<SchoolsAndDirectors>>()
    val subjects = MutableLiveData<List<SubjectWithStudents>>()
    val directorWithSchoolName = MutableLiveData<List<SchoolsAndDirectors>>()
    private val lifecycle = owner.lifecycle

    companion object {
        const val TAG = "MainViewModel"
    }

  private val dummies:MutableLiveData<List<Dummy>> by lazy{
      MutableLiveData<List<Dummy>>()
  }

   val mDummies:LiveData<List<Dummy>>
   get() = dummies

    val schoolList = listOf<School>(School("Kotlin"), School("Java"), School("JetBrains"))
    val directors = listOf<Director>(
            Director("John Mizz", "Kotlin"),
            Director("Amesha carol", "Java"),
            Director("tonmer Crott", "JetBrains"))
   /* val students = listOf<Student>(
            Student("peter", 3, "Kotlin"),
            Student("Harry", 2, "Java"),
            Student("Johan", 5, "JetBrains"),
            Student("Pryag", 2, "Kotlin"),
            Student("Aman", 4, "Java"),
            Student("sam",6,"Kotlin"))*/

   /* val subjectsData = listOf<Subject>(
            Subject("Data Structure"),
            Subject("kotlin"),
            Subject("communication"),
            Subject("C++"),
            Subject("Kotlin newbie"),
            Subject("Meditation")
    )*/

    val studenSubjectRelations = listOf<StudentSubjectCrossRef>(
            StudentSubjectCrossRef("peter","Data Structure"),
            StudentSubjectCrossRef("Harry","kotlin"),
            StudentSubjectCrossRef("Johan","communication"),
            StudentSubjectCrossRef("Pryag","C++"),
            StudentSubjectCrossRef("Aman","Kotlin newbie"),
            StudentSubjectCrossRef("sam","Meditation")
    )

    init {

        lifecycle.addObserver(this)
        directors.forEach {
            compositeDisposable.add(
                    databaseService.schoolDao().insertDirector(it).subscribeOn(Schedulers.io()).subscribe({
                      //  director.postValue("Added")
                    },
                            {
                                Log.d(TAG, it.toString())
                            })

            )
        }
        schoolList.forEach {
            compositeDisposable.add(
                    databaseService.schoolDao().insertSchool(it).subscribeOn(Schedulers.io()).subscribe({
                       // school.postValue("Added")
                    }, {

                    })
            )
        }

       /* students.forEach {
            compositeDisposable.add(
                    databaseService.schoolDao().insertStudents(it).subscribeOn(Schedulers.io()).subscribe {
                        Log.d(TAG, "Students added")
                    }
            )
        }*/

       /* subjectsData.forEach {
            compositeDisposable.add(
                    databaseService.schoolDao().insertSubject(it).subscribeOn(Schedulers.io()).subscribe {
                        Log.d(TAG, "Subjects added")
                    }
            )
        }*/

        studenSubjectRelations.forEach {
            compositeDisposable.add(
                    databaseService.schoolDao().insertStudentSubjectCrossRef(it).subscribeOn(Schedulers.io()).subscribe{
                        Log.d(TAG, "Subjects and Students Relations added")
                    }
            )
        }


    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun getSchoolData(){
        compositeDisposable.add(
                databaseService.schoolDao().getSchoolsAndDirectorsWithSchoolName("Kotlin").subscribeOn(Schedulers.io()).subscribe({
                    schoolData.postValue(it)
                },{
                    Log.d(TAG, it.toString())
                })
        )
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun getSubjects(){
        compositeDisposable.add(
                databaseService.schoolDao().getAllStudentOfSubject("Data Structure").subscribeOn(Schedulers.io()).subscribe({
                    subjects.postValue(it)
                },{
                    Log.d(TAG, it.toString())
                })
        )
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun getDirectors(){
        compositeDisposable.add(
                databaseService.schoolDao().getSchoolsAndDirectorsWithSchoolName("Kotlin").subscribeOn(Schedulers.io()).subscribe({
                                      directorWithSchoolName.postValue(it)
                },{
                    Log.d(TAG, it.toString())
                })
        )
    }



    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun getDummies(){
        compositeDisposable.add(
                networkService.doDummyCall(DummyReq("123"))
                        .subscribeOn(Schedulers.io())
                        .subscribe({
                            dummies.postValue(it.data)
                        },{
                            Log.d(TAG,it.toString())
                        })
        )
    }




    /* val someData: String
         get() = databaseService.dummyData + " : " + networkService.dummyData*/
}
