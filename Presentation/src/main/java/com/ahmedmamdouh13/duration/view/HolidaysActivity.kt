package com.ahmedmamdouh13.duration.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.ahmedmamdouh13.duration.R
import com.ahmedmamdouh13.duration.adapter.RecyclerAdapter
import com.ahmedmamdouh13.duration.viewmodel.HolidaysViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import org.koin.android.viewmodel.ext.android.viewModel

class HolidaysActivity : AppCompatActivity() {

    //TODO design HolidaysActivity ui , implement app main function (duration) notify.

    private val mViewModel : HolidaysViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mViewModel.run {
            msg.observe(this@HolidaysActivity, Observer { msg ->
            println(msg)
            Toast.makeText(this@HolidaysActivity,msg,Toast.LENGTH_LONG).show()
        })
        }

        mViewModel.run {
            viewList.observe(this@HolidaysActivity, Observer {
                recyclerview_activitymain.layoutManager = LinearLayoutManager(this@HolidaysActivity,LinearLayoutManager.VERTICAL,false)
                recyclerview_activitymain.itemAnimator = DefaultItemAnimator()
                recyclerview_activitymain.adapter = RecyclerAdapter().apply { list = it }
            })
        }

        GlobalScope.launch {
            mViewModel.getHolidayListInLocation("US")
        }



    }

//    override fun toast(message: String) {
//    }



}
