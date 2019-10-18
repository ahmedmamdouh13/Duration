package com.ahmedmamdouh13.duration.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ahmedmamdouh13.duration.R
import com.ahmedmamdouh13.duration.model.TaskModel
import kotlinx.android.synthetic.main.item_task.view.*

class TaskListAdapter : RecyclerView.Adapter<TaskListAdapter.TaskViewHolder>() {

    internal var list: ArrayList<TaskModel> = arrayListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_task,null))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(list[position])
    }

    inner class TaskViewHolder(private val v : View) : RecyclerView.ViewHolder(v) {
        fun bind(taskModel: TaskModel) {
            v.tasktitle_itemtask.text = taskModel.title
            v.tasktag_itemtask.text = taskModel.tag
        }
    }

    fun addNewItem(item:TaskModel){
        list.add(item)
        this.notifyItemInserted(list.size-1)
    }
}
