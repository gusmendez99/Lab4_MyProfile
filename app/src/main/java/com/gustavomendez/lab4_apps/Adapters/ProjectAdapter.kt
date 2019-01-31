package com.gustavomendez.lab4_apps.Adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gustavomendez.lab4_apps.Model.Project
import com.gustavomendez.lab4_apps.R
import kotlinx.android.synthetic.main.project_list_item.view.*

class ProjectAdapter(
    private val items : ArrayList<Project>, private val context: Context,
    private val listener: (Project) -> Unit) : RecyclerView.Adapter<ProjectAdapter.ViewHolder>() {

    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return items.size
    }

    // Inflates the item views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.project_list_item, parent, false))
    }

    // Binds each animal in the ArrayList to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(items[position],listener)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(project: Project, listener: (Project) -> Unit) = with(itemView) {
            itemView.tv_project_title.text = project.title
            itemView.tv_project_description.text = project.description
            setOnClickListener { listener(project) }
        }
    }
}