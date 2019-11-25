package com.ahmedmamdouh13.duration.view

import android.os.Bundle
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.ahmedmamdouh13.customcalendarview.CalendarFragment
import com.ahmedmamdouh13.duration.R
import com.ahmedmamdouh13.duration.adapter.TaskListAdapter
import com.ahmedmamdouh13.duration.view.base.BaseActivity
import com.ahmedmamdouh13.duration.view.fragment.AddTaskFragment
import com.ahmedmamdouh13.duration.view.fragment.ProjectFragment
import com.ahmedmamdouh13.duration.viewmodel.AddProjectViewModel
import kotlinx.android.synthetic.main.activity_add_project.*
import org.koin.android.viewmodel.ext.android.viewModel

class AddProjectActivity : BaseActivity(), CalendarFragment.CalendarInterface,
    ProjectFragment.ProjectFragmentInterface, AddTaskFragment.AddTaskFragmentInterface {


    private var projectKey: Int = 0
    private val myViewModel: AddProjectViewModel by viewModel()
    private val taskListAdapter = TaskListAdapter()

    override val getContentView: Int = R.layout.activity_add_project

    override fun onViewCreated(savedInstanceState: Bundle?) {

        recyclerview_tasks_addprojectactivity.adapter = taskListAdapter
        recyclerview_tasks_addprojectactivity.itemAnimator = DefaultItemAnimator()
        recyclerview_tasks_addprojectactivity.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )


//        myViewModel.updateUISuccess.observe(this, Observer {
//
//            Snackbar.make(
//                container_linearlayout_addprojectactivity,
//                it.message,
//                Snackbar.LENGTH_LONG
//            ).show()
//            projectKey = it.data.toInt()
//
//            getTasks(it.data.toInt())
//
//
//        })
//        myViewModel.updateUIFailed.observe(this, Observer {
//            Snackbar.make(container_linearlayout_addprojectactivity, it, Snackbar.LENGTH_LONG)
//                .show()
//
//        })

//        if (intent.getIntExtra("id", -1) == -1) {
//            supportFragmentManager.beginTransaction()
//                .add(ProjectFragment().apply {
//                    projectFragmentInterface = this@AddProjectActivity
//                    arguments = Bundle().apply {
//                        putString("msg", "Set Project Title")
//                        putInt("color", R.color.colorAccent)
//                    }
//                }
//                    , "projectfragment")
//                .commit()
//        } else {
//            GlobalScope.launch(Dispatchers.Main) {
//                val id = intent.getIntExtra("id", -1)
//                myViewModel.getProjectLocally(id)
//                projectKey = id
//                getTasks(id)
//                myViewModel.title.observe(this@AddProjectActivity, Observer {
//                    title_edittext_addprojcectactivity.text = it
//                })
//                myViewModel.deadLine.observe(this@AddProjectActivity, Observer {
//                    date_addprojectactivity.text = it
//                })
//            }
//        }


//        Snackbar.make(container_linearlayout_addprojectactivity,"Add ",Snackbar.LENGTH_LONG).show()

//        displayMsg("Set Deadline Date",container_linearlayout_addprojectactivity)
        fab_addtask_addprojectactivity.setOnClickListener {

//            supportFragmentManager.beginTransaction()
//                .add(AddTaskFragment().apply {
//                    addTaskFragment = this@AddProjectActivity
//                    arguments = Bundle().apply {
//                        putString("msg", "Set Deadline Date")
//                        putInt("color", R.color.colorAccent)
//                    }
//                }, "calendarfragment")
//                .commit()

        }


    }

    private fun getTasks(data: Int) {
//        GlobalScope.launch(Dispatchers.Main) {
//
//            myViewModel.getListObserver(data).observe(this@AddProjectActivity, Observer { list ->
//
//                placeholder_container_addprojectactivity.visibility = View.GONE
//                for (taskDomain in list) {
//                    taskListAdapter.addNewItem(taskDomain)
//                }
//            })
    }




override fun onDismissAddTaskFragment(title: String, tag: String) {
//        GlobalScope.launch {
//            myViewModel.addTask(
//                title,
//                tag,
//                projectKey,
//                title_edittext_addprojcectactivity.text.toString()
//            )
//        }

}

override fun onDismissProjectFragment(title: String) {
//        title_edittext_addprojcectactivity.text = title
//        myViewModel.title.postValue(title)
//        supportFragmentManager.beginTransaction()
//            .add(CalendarFragment().apply {
//                mCalendarInterface = this@AddProjectActivity
//                arguments = Bundle().apply {
//                    putString("msg", "Set Deadline Date")
//                    putInt("color", R.color.colorAccent)
//                }
//            }, "calendarfragment")
//            .commit()
}

override fun dateCallBack(date: String) {
//        GlobalScope.launch {
//            myViewModel.addProject(
//                title_edittext_addprojcectactivity.text.toString(),
//                "TODAY",
//                date
//            )
//        }
}

override fun visibleDateCallBack(date: String) {
//        date_addprojectactivity.text = date
}
}
