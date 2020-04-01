package com.julio.platziconf.view.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager

import com.julio.platziconf.R
import com.julio.platziconf.model.Speaker
import com.julio.platziconf.view.adapter.SpeakerAdapter
import com.julio.platziconf.view.adapter.SpeakerListener
import com.julio.platziconf.viewmodel.SpeakerViewModel
import kotlinx.android.synthetic.main.fragment_speakers.*

/**
 * A simple [Fragment] subclass.
 */
class SpeakersFragment : Fragment(),SpeakerListener {

    private lateinit var speakerAdapter:SpeakerAdapter
    private lateinit var viewModel: SpeakerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_speakers, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(SpeakerViewModel::class.java)
        viewModel.refresh()

        speakerAdapter = SpeakerAdapter(this)

        rvSpeakers.apply {
            layoutManager = GridLayoutManager(view.context,2)
            adapter = speakerAdapter
        }
        observeViewModel()

    }
    fun observeViewModel(){
        viewModel.listSpeaker.observe(this, Observer<List<Speaker>>{ schedule->
            speakerAdapter.updateData(schedule)
        })
        viewModel.isLoading.observe(this,Observer<Boolean>{
            if(it !=null)
                rvSpeakersBase.visibility=View.INVISIBLE
        })
    }

    override fun onSpeakerClick(speaker: Speaker, position: Int) {
        val bundle = bundleOf("Speaker" to speaker)
        findNavController().navigate(R.id.speakersDetailFragmentDialog,bundle)
    }

}

