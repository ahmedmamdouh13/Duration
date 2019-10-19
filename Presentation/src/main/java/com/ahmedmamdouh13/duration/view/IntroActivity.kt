package com.ahmedmamdouh13.duration.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.lifecycle.Observer
import com.ahmedmamdouh13.duration.R
import com.ahmedmamdouh13.duration.model.ProjectModel
import com.ahmedmamdouh13.duration.view.base.BaseActivity
import com.ahmedmamdouh13.duration.viewmodel.IntroViewModel
import kotlinx.android.synthetic.main.activity_intro.*
import kotlinx.android.synthetic.main.item_projects.view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel

class IntroActivity : BaseActivity() {

    private val introViewModel : IntroViewModel by viewModel()
    val hashMap = HashMap<String, String>()
    init {
        hashMap["str"] = " "
    }
    private var str:String by hashMap

    override val getContentView: Int = R.layout.activity_intro

    override fun onViewCreated(savedInstanceState: Bundle?) {

        applyCustomColors(container_activityintro,appbar_introactivity)
        add_fab_introactivity.setOnClickListener { startActivity(Intent(this,AddProjectActivity::class.java)) }
        toolbar_activityintro.inflateMenu(R.menu.themes_menu)
        toolbar_activityintro.setOnMenuItemClickListener {
            changeColors(container_activityintro,appbar_introactivity)
            true
        }
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
//                this.start_date_itemproject.text = it.startDate
//                this.end_date_itemproject.text = it.endDate
//                 this.setBackgroundColor(
//                        ContextCompat.getColor(
//                            this@IntroActivity,
//                            R.color.colorPrimary
//                        ))

                println(it.title + " " + it.key)

                linearLayoutContainer_introactivity.addView(this)

            }
            .setOnClickListener {
                startActivity(Intent(this,ProjectActivity::class.java))
            }
    }
}

