package com.ahmedmamdouh13.duration.presentation.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.ahmedmamdouh13.duration.R
import com.ahmedmamdouh13.duration.presentation.adapter.RecyclerAdapter
import com.ahmedmamdouh13.duration.presentation.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {


    private val mViewModel : MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mViewModel.msg.observe(this, Observer {msg ->
            println(msg)
            Toast.makeText(this,msg,Toast.LENGTH_LONG).show()
        })

        mViewModel.viewList.observe(this, Observer {
            recyclerview_activitymain.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
            recyclerview_activitymain.itemAnimator = DefaultItemAnimator()
            recyclerview_activitymain.adapter = RecyclerAdapter().apply { list = it }
        })

        GlobalScope.launch {
            mViewModel.getHolidayListInLocation("EG")
        }


    }

//    override fun toast(message: String) {
//    }



}
