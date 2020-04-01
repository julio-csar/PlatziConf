package com.julio.platziconf.view.ui.fragments

import android.app.Dialog
import android.app.DialogFragment.STYLE_NORMAL
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

import com.julio.platziconf.R
import com.julio.platziconf.model.Speaker
import kotlinx.android.synthetic.main.fragment_speakers_detail_dialog.*

/**
 * A simple [Fragment] subclass.
 */
class SpeakersDetailDialogFragment : DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL,R.style.FullScreenDialogStyle)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_speakers_detail_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbarSpeakerDetailToolbar.navigationIcon =
            ContextCompat.getDrawable(view.context, R.drawable.ic_close);
        toolbarSpeakerDetailToolbar.setTitleTextColor(Color.WHITE);
        toolbarSpeakerDetailToolbar.setNavigationOnClickListener {
            dismiss()
        }

        val speaker = arguments?.getSerializable("Speaker") as Speaker
        toolbarSpeakerDetailToolbar.title = speaker.name

        tvSpeakerDetailName.text = speaker.name
        tvSpeakerDetailTitle.text = speaker.jobTitle
        tvSpeakerDetailJob.text = speaker.workPlace
        tvSpeakerDetailDescription.text =speaker.biography

        Glide.with(this)
            .load(speaker.image)
            .apply(RequestOptions.circleCropTransform())
            .into(ivSpeakerDetailImage)

    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT)
    }
}
