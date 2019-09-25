package com.ahmedmamdouh13.duration.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.ahmedmamdouh13.duration.R
import com.ahmedmamdouh13.duration.presentation.model.HolidaysModel
import com.ahmedmamdouh13.duration.presentation.view.MainView
import com.ahmedmamdouh13.duration.presentation.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(),MainView {


    private val mViewModel : MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mViewModel.bind(this)

        GlobalScope.launch {
            mViewModel.getHolidayListInLocation("EG")
        }

    }

    override fun toast(message: String) {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show()
    }

    override fun displayList(list: List<HolidaysModel>) {

    }

}
