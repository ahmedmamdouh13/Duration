package com.ahmedmamdouh13.duration.presentation.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.ahmedmamdouh13.duration.R
import com.ahmedmamdouh13.duration.presentation.model.ProjectModel
import com.ahmedmamdouh13.duration.presentation.view.base.BaseActivity
import com.ahmedmamdouh13.duration.presentation.viewmodel.IntroViewModel
import kotlinx.android.synthetic.main.activity_intro.*
import kotlinx.android.synthetic.main.item_projects.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.android.ext.android.get
import org.koin.android.viewmodel.ext.android.viewModel
import kotlin.reflect.KProperty

class IntroActivity : BaseActivity() {

    private val introViewModel : IntroViewModel by viewModel()
    val hashMap = HashMap<String, String>()
    init {
        hashMap["str"] = " "
    }
    private var str:String by hashMap

    override val getContentView: Int = R.layout.activity_intro

    override fun onViewCreated(savedInstanceState: Bundle?) {

        add_fab_introactivity.setOnClickListener { startActivity(Intent(this,AddProjectActivity::class.java)) }

        introViewModel.projectLiveData.observe(this, Observer {
            for (projectModel in it) {
                addProjectsToView(projectModel)
            }
            displayMsg("Your Projects !",container_activityintro)
        })

        GlobalScope.launch{
          introViewModel.initProjects()
        }

        println(str)

    }

    private fun addProjectsToView(it: ProjectModel) {
        LayoutInflater.from(this).inflate(R.layout.item_projects, null)
            .apply {
                this.title_itemprojects.text = it.title
                this.start_date_itemproject.text = it.startDate
                this.end_date_itemproject.text = it.endDate
                when(it.key%2) {
                  0 ->  this.setBackgroundColor(
                        ContextCompat.getColor(
                            this@IntroActivity,
                            R.color.colorPrimary
                        )
                    )
                    else -> this.setBackgroundColor(
                        ContextCompat.getColor(
                            this@IntroActivity,
                            R.color.evenItemColor
                        )
                    )
                }
                println(it.title + " " + it.key)

                linearLayoutContainer_introactivity.addView(this)

            }
            .setOnClickListener {
                startActivity(Intent(this,ProjectActivity::class.java))
            }
    }
}

