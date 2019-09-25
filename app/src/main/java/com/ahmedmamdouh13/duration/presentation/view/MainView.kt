package com.ahmedmamdouh13.duration.presentation.view

import com.ahmedmamdouh13.duration.presentation.model.HolidaysModel

interface MainView {
    fun toast(message: String)
    fun displayList(list: List<HolidaysModel>)
}
