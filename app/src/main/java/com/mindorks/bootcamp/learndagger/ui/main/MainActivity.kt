    package com.mindorks.bootcamp.learndagger.ui.main

import android.os.Bundle
import android.widget.TextView

import com.mindorks.bootcamp.learndagger.MyApplication
import com.mindorks.bootcamp.learndagger.R
import com.mindorks.bootcamp.learndagger.di.component.DaggerActivityComponent
import com.mindorks.bootcamp.learndagger.di.module.ActivityModule
import com.mindorks.bootcamp.learndagger.ui.home.HomeFragment

import javax.inject.Inject

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.mindorks.bootcamp.learndagger.data.local.DatabaseService
import com.mindorks.bootcamp.learndagger.entities.relations.SchoolsAndDirectors
import com.mindorks.bootcamp.learndagger.entities.relations.SubjectWithStudents
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        getDependencies()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val directors = findViewById<TextView>(R.id.directors)
        val schools = findViewById<TextView>(R.id.schools)
        val students = findViewById<TextView>(R.id.students)
        val dummies = findViewById<TextView>(R.id.dummies)


        viewModel.directorWithSchoolName.observe(this,object:Observer<List<SchoolsAndDirectors>>{
            override fun onChanged(t: List<SchoolsAndDirectors>?) {
               directors.text = t.toString()
            }

        })

        viewModel.subjects.observe(this,object:Observer<List<SubjectWithStudents>>{
            override fun onChanged(t: List<SubjectWithStudents>?) {
               students.text = t.toString()
            }

        })

        viewModel.schoolData.observe(this,object:Observer<List<SchoolsAndDirectors>>{
            override fun onChanged(t: List<SchoolsAndDirectors>?) {
               schools.text = t.toString()
            }
        })

        //addHomeFragment()

        viewModel.mDummies.observe(this, Observer {
            dummies.text = it.toString()
        })


    }



    private fun addHomeFragment() {
        if (supportFragmentManager.findFragmentByTag(HomeFragment.TAG) == null) {
            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.container_fragment, HomeFragment.newInstance(), HomeFragment.TAG)
                    .commit()
        }
    }

    private fun getDependencies() {
        DaggerActivityComponent
                .builder()
                .applicationComponent((application as MyApplication).applicationComponent)
                .activityModule(ActivityModule(this))
                .build()
                .inject(this)
    }

}
