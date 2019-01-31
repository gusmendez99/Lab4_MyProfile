package com.gustavomendez.lab4_apps.Fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.gustavomendez.lab4_apps.Adapters.ProjectAdapter
import com.gustavomendez.lab4_apps.Model.Project

import com.gustavomendez.lab4_apps.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ProjectFragment : Fragment() {

    var didClickProject: ((String, String) -> Unit)? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_project, container, false)

        (activity as AppCompatActivity).supportActionBar!!.title = "Proyectos"

        val myProjects = arrayListOf<Project>(
            Project("Project 2", "loren ipsum smfks skfmsk msk sk mfks mfks fksm fksm fkssfk msk mfsk msk",
                "https://github.com/gusmendez99/lab2_orders"),
            Project("Project 3", "loren ipsum smfks skfmsk msk sk mfks mfks fksm fksm fkssfk msk mfsk msk",
                "https://github.com/gusmendez99/lab3-contacts")
        )

        var rvProjects = view.findViewById<View>(R.id.rv_projects) as RecyclerView
        rvProjects.layoutManager = LinearLayoutManager(this.context)

        //Setting the recycler adapter
        rvProjects.adapter = ProjectAdapter(myProjects, this.context!!) { project ->

            run {
                //Get a callback with the project info
                //TODO: Open window for
                Log.d("OnClick", "Were gonna open URL from ${project.title}")
                didClickProject?.invoke(project.url, project.title)

            }
        }

        return view
    }


}
