package com.ahmedmamdouh13.duration.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ahmedmamdouh13.duration.R
import com.ahmedmamdouh13.duration.model.HolidaysModel
import kotlinx.android.synthetic.main.item_holidays.view.*

class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.mViewHolder>() {

    internal lateinit var list : List<HolidaysModel>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): mViewHolder =
        mViewHolder(View.inflate(parent.context, R.layout.item_holidays,null))


    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: mViewHolder, position: Int) {

        holder.bind(list[position])

    }

    class mViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(holidaysModel: HolidaysModel) {
            itemView.date_item_holidays.text = holidaysModel.date
            itemView.title_item_holidays.text = holidaysModel.name
            itemView.duration_item_holidays.text = holidaysModel.desc
        }


    }

}
