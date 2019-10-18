package com.ahmedmamdouh13.duration.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import com.ahmedmamdouh13.duration.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_project.view.*

class ProjectFragment : DialogFragment() {

    private var colorBackground: Int = 0
    private lateinit var text: String
    lateinit var projectFragmentInterface: ProjectFragmentInterface

interface ProjectFragmentInterface{
    fun onDismissProjectFragment(title: String)
}

    override fun onStart() {
        super.onStart()
        if (dialog != null) {
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.MATCH_PARENT
            this.dialog?.window!!.setLayout(width, height)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.arguments?.getString("msg")?.let {
            text = it
        }

        this.arguments?.getInt("color")?.let {
            colorBackground = it
        }

        this.setStyle(STYLE_NO_FRAME, com.ahmedmamdouh13.customcalendarview.R.style.transparentTheme)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        this.dialog?.window!!.requestFeature(Window.FEATURE_NO_TITLE)
//        this.dialog?.window!!.setBackgroundDrawableResource(colorBackground)


        val view = inflater.inflate(R.layout.fragment_project, null)


        view.fab_projectfragment.setOnClickListener {
            projectFragmentInterface.onDismissProjectFragment(view.title_edittext_projcectfragment.text.toString())
            dismiss()
        }
        Snackbar.make(view.container_fragmentproject,text,
            Snackbar.LENGTH_INDEFINITE).show()


        return view
    }
}