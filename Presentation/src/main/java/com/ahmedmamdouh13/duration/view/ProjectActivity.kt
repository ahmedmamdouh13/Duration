package com.ahmedmamdouh13.duration.view

import android.os.Bundle
import androidx.lifecycle.Observer
import com.ahmedmamdouh13.duration.R
import com.ahmedmamdouh13.duration.view.base.BaseActivity
import com.ahmedmamdouh13.duration.viewmodel.ProjectViewModel
import kotlinx.android.synthetic.main.activity_project.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel

class ProjectActivity : BaseActivity() {

    private val projectViewModel: ProjectViewModel by viewModel()

    override val getContentView: Int = R.layout.activity_project

    override fun onViewCreated(savedInstanceState: Bundle?) {

        projectViewModel.displayMsgLiveData.observe(this, Observer {
            displayMsg(it,container_activityproject)
        })


        GlobalScope.launch {
            projectViewModel.initHolidays(timeZone)
        }
    }
}
