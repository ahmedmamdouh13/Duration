package com.ahmedmamdouh13.duration.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ahmedmamdouh13.duration.R
import com.ahmedmamdouh13.duration.model.ProjectModel
import kotlinx.android.synthetic.main.item_projects.view.*

//TODO : Migrate to ListAdapter and DiffUtil.

class ProjectListAdapter : RecyclerView.Adapter<ProjectListAdapter.MyViewHolder>() {

    internal var list: ArrayList<ProjectModel> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder =
        MyViewHolder(View.inflate(parent.context, R.layout.item_projects, null))


    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.bind(list[position])

    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(model: ProjectModel) {
            itemView.title_itemprojects.text = model.title
//            itemView. .text = model.endDate
            itemView.lottie_item_projects.setOnClickListener {
                itemView.lottie_item_projects.playAnimation()
            }
//            itemView.container_framelayout_itemprojects.setOnClickListener {
//                itemView.context.startActivity(Intent(itemView.context,AddProjectActivity::class.java)
//                    .apply {
//                        putExtra("id",model.key)
//                    }
//                )
//            }
        }


    }

}
