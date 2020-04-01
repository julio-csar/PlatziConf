package com.julio.platziconf.view.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager

import com.julio.platziconf.R
import com.julio.platziconf.model.Conference
import com.julio.platziconf.view.adapter.ScheduleAdapter
import com.julio.platziconf.view.adapter.ScheduleListener
import com.julio.platziconf.viewmodel.ScheduleViewModel
import kotlinx.android.synthetic.main.fragment_schedule.*

/**
 * A simple [Fragment] subclass.
 */
class ScheduleFragment : Fragment(),ScheduleListener {

    private lateinit var scheduleAdapter:ScheduleAdapter
    private lateinit var viewModel:ScheduleViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_schedule, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(ScheduleViewModel::class.java)
        viewModel.refresh()

        scheduleAdapter = ScheduleAdapter(this)

        rvScheduleList.apply {
            layoutManager = LinearLayoutManager(view.context,LinearLayoutManager.VERTICAL,false)
            adapter = scheduleAdapter
        }
        observeViewModel()

    }
    fun observeViewModel(){
        viewModel.listSchedule.observe(this, Observer<List<Conference>>{ schedule->
            scheduleAdapter.updateData(schedule)
        })
        viewModel.isLoading.observe(this,Observer<Boolean>{
            if(it !=null)
                rvScheduleProgress.visibility=View.INVISIBLE
        })
    }

    override fun onConferenceClick(conference: Conference, position: Int) {
        val bundle = bundleOf("Conference" to conference)
        findNavController().navigate(R.id.scheduleDetailFragmentDialog,bundle)
    }

}
