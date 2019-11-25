package com.ahmedmamdouh13.duration.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.ahmedmamdouh13.duration.R
import com.ahmedmamdouh13.duration.adapter.ProjectListAdapter
import com.ahmedmamdouh13.duration.model.ProjectModel
import com.ahmedmamdouh13.duration.view.base.BaseActivity
import com.ahmedmamdouh13.duration.viewmodel.IntroViewModel
import kotlinx.android.synthetic.main.activity_intro.*
import kotlinx.android.synthetic.main.bottom_menu.view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel

class IntroActivity : BaseActivity() {

    private lateinit var menuSheetView: View
    private var index: Int = -1
    private val introViewModel: IntroViewModel by viewModel()
    private val hashMap = HashMap<String, String>()

    init {
        hashMap["str"] = " "
    }

    private var str: String by hashMap

    override val getContentView: Int = R.layout.activity_intro

    override fun onViewCreated(savedInstanceState: Bundle?) {

        applyCustomColors(container_activityintro, appbar_introactivity)
        add_fab_introactivity.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    AddProjectActivity::class.java
                )
            )
        }
        searchmenu_imageview_activityintro.setOnClickListener {
            //TODO Search for tasks tags/names
            true
        }

        drawermenu_imageview_activityintro.setOnClickListener {
            showCustomBottomSheet()
        }

        projectlist_recyclerview_activityintro.adapter = ProjectListAdapter()
        projectlist_recyclerview_activityintro.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        projectlist_recyclerview_activityintro.itemAnimator = DefaultItemAnimator()

        introViewModel.projectLiveData.observe(this, Observer {
            for (projectModel in it) {
                addProjectsToView(projectModel)
            }
            displayMsg("Your Projects !", container_activityintro)
        })

        GlobalScope.launch {
            introViewModel.initProjects()
        }
        println(str)



        menuSheetView = LayoutInflater.from(this).inflate(
            R.layout.bottom_menu,
            null,
            false
        )
        menuSheetView.create_mode_item_bottommenu.setOnClickListener {

            startActivity(Intent(this, CreateModeActivity::class.java))

        }
        menuSheetView.about_item_bottommenu.setOnClickListener{

        }
        menuSheetView.settings_item_bottommenu.setOnClickListener {

        }
        menuSheetView.stats_item_bottommenu.setOnClickListener {

        }

    }


    private fun addProjectsToView(it: ProjectModel) {
        index++
        (projectlist_recyclerview_activityintro.adapter as ProjectListAdapter)
            .apply {
                list.add(it)
                notifyItemInserted(index)
            }
    }


    private fun showCustomBottomSheet() {
//        bottomsheet.showWithSheetView(menuSheetView)

        bottomsheet.showWithSheetView(
            menuSheetView
        )


    }
}

