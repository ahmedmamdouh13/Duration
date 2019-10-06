package com.ahmedmamdouh13.duration.presentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import com.ahmedmamdouh13.duration.R
import com.ahmedmamdouh13.duration.presentation.view.base.BaseActivity
import com.ahmedmamdouh13.duration.presentation.viewmodel.AddProjectViewModel
import com.aminography.primecalendar.PrimeCalendar
import com.aminography.primecalendar.civil.CivilCalendar
import com.aminography.primedatepicker.OnDayPickedListener
import com.aminography.primedatepicker.PickType
import com.aminography.primedatepicker.fragment.PrimeDatePickerBottomSheet
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_add_project.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.android.scope.currentScope
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.*

class AddProjectActivity : BaseActivity() {

    private val mviewModel : AddProjectViewModel by viewModel()

    override val getContentView: Int = R.layout.activity_add_project
    override fun onViewCreated(savedInstanceState: Bundle?) {

        mviewModel.updateUISuccess.observe(this, Observer {
            Snackbar.make(container_linearlayout_addprojectactivity,it,Snackbar.LENGTH_LONG).show()
//            onBackPressed()
        })
        mviewModel.updateUIFailed.observe(this, Observer {
            Snackbar.make(container_linearlayout_addprojectactivity,it,Snackbar.LENGTH_LONG).show()

        })

        val pickerBottomSheet =
            PrimeDatePickerBottomSheet.newInstance(CivilCalendar(), pickType = PickType.RANGE_START)
        pickerBottomSheet
            .setOnDateSetListener(object : PrimeDatePickerBottomSheet.OnDayPickedListener {
                override fun onRangeDaysPicked(startDay: PrimeCalendar, endDay: PrimeCalendar) {
                    println("${startDay.getTime()} ${endDay.getTime()}")
                }

                override fun onSingleDayPicked(singleDay: PrimeCalendar) {
                    println("$singleDay")
                }

            })


        pickerBottomSheet.show(supportFragmentManager.beginTransaction(),"picker")

        GlobalScope.launch {
            mviewModel.addProject("add add add ","3/9/2019","18/10/2019")

        }


    }
}
