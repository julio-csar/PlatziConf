package com.julio.platziconf.view.ui.fragments

import android.app.Dialog
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment

import com.julio.platziconf.R
import com.julio.platziconf.model.Conference
import kotlinx.android.synthetic.main.fragment_schedule_detail_dialog.*
import java.text.SimpleDateFormat

/**
 * A simple [Fragment] subclass.
 */
class ScheduleDetailDialogFragment : DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL,R.style.FullScreenDialogStyle)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_schedule_detail_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbarSheduleDetailToolbar.navigationIcon =
            ContextCompat.getDrawable(view.context, R.drawable.ic_close);
        toolbarSheduleDetailToolbar.setTitleTextColor(Color.WHITE);
        toolbarSheduleDetailToolbar.setNavigationOnClickListener {
            dismiss()
        }

        val conference = arguments?.getSerializable("Conference") as Conference
        toolbarSheduleDetailToolbar.title = conference.title

        tvScheduleDetailTitle.text=conference.title
        val pattern = "dd/MM/aa hh:mm a"
        val simpleDF = SimpleDateFormat(pattern)
        tvScheduleDetailHour.text = simpleDF.format(conference.datetime)
        tvScheduleDetailSpeaker.text = conference.speaker
        tvScheduleDetailTag.text = conference.tag
        tvScheduleDetailDescription.text = conference.description
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT)
    }
}
