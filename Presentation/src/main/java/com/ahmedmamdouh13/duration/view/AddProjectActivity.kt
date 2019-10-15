package com.ahmedmamdouh13.duration.view

import android.os.Bundle
import androidx.lifecycle.Observer
import com.ahmedmamdouh13.customcalendarview.CalendarFragment
import com.ahmedmamdouh13.duration.R
import com.ahmedmamdouh13.duration.view.base.BaseActivity
import com.ahmedmamdouh13.duration.viewmodel.AddProjectViewModel
import kotlinx.android.synthetic.main.activity_add_project.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel

class AddProjectActivity : BaseActivity(), CalendarFragment.CalendarInterface,
    ProjectFragment.ProjectFragmentInterface, AddTaskFragment.AddTaskFragmentInterface {



    private val mviewModel : AddProjectViewModel by viewModel()

    override val getContentView: Int = R.layout.activity_add_project

    override fun onViewCreated(savedInstanceState: Bundle?) {

        mviewModel.updateUISuccess.observe(this, Observer {
        })
        mviewModel.updateUIFailed.observe(this, Observer {
//            Snackbar.make(container_linearlayout_addprojectactivity,it,Snackbar.LENGTH_LONG).show()

        })

        supportFragmentManager.beginTransaction()
            .add(ProjectFragment().apply {
                projectFragmentInterface = this@AddProjectActivity
                arguments = Bundle().apply {
                    putString("msg","Set Project Title")
                    putInt("color",R.color.colorAccent)
                }
            }
                ,"projectfragment")
            .commit()



//        Snackbar.make(container_linearlayout_addprojectactivity,"Add ",Snackbar.LENGTH_LONG).show()

//        displayMsg("Set Deadline Date",container_linearlayout_addprojectactivity)
fab_addtask_addprojectactivity.setOnClickListener {

    supportFragmentManager.beginTransaction()
        .add(AddTaskFragment().apply {
            addTaskFragment = this@AddProjectActivity
            arguments = Bundle().apply {
                putString("msg","Set Deadline Date")
                putInt("color",R.color.colorAccent)
            }
        },"calendarfragment")
        .commit()

}


        GlobalScope.launch {
          //  mviewModel.addProject("add add add ","3/9/2019","18/10/2019")

        }


    }

    override fun onDismissAddTaskFragment(title: String, tag: String) {

    }
    override fun onDismissProjectFragment(title: String) {
        title_edittext_addprojcectactivity.text = title
        mviewModel.title.postValue(title)
        supportFragmentManager.beginTransaction()
            .add(CalendarFragment().apply {
                mCalendarInterface = this@AddProjectActivity
                arguments = Bundle().apply {
                    putString("msg","Set Deadline Date")
                    putInt("color",R.color.colorAccent)
                }
            },"calendarfragment")
            .commit()
    }

    override fun dateCallBack(date: String) {
        date_addprojectactivity.text = date
        mviewModel.deadLine.postValue(date)


    }
}
