package com.julio.platziconf.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.julio.platziconf.R
import com.julio.platziconf.model.Conference
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ScheduleAdapter(val scheduleListener:ScheduleListener) : RecyclerView.Adapter<ScheduleAdapter.ViewHolder>() {
    var listConference = ArrayList<Conference>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_schedule, parent, false)
    )

    override fun getItemCount() = listConference.size

    override fun onBindViewHolder(holder: ScheduleAdapter.ViewHolder, position: Int) {
        val conference = listConference.get(position) as Conference
        holder.tvConferenceName.text = conference.title
        holder.tvItemScheduleSpeaker.text = conference.speaker
        holder.tvItemScheduleTag.text = conference.tag

        val simpleDateFormat = SimpleDateFormat("HH:mm")
        val simpleDateFormatAMPM = SimpleDateFormat("a")

        val calendar = Calendar.getInstance()
        calendar.time = conference.datetime

        holder.tvItemScheduleHour.text = simpleDateFormat.format(conference.datetime)
        holder.tvItemScheduleAMPM.text = simpleDateFormatAMPM.format(conference.datetime).toUpperCase()

        holder.itemView.setOnClickListener{
            scheduleListener.onConferenceClick(conference,position)
        }
    }

    fun updateData(data:List<Conference>){
        listConference.clear()
        listConference.addAll(data)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvConferenceName = itemView.findViewById<TextView>(R.id.tvItemScheduleConference)
        val tvItemScheduleAMPM = itemView.findViewById<TextView>(R.id.tvItemScheduleAMPM)
        val tvItemScheduleHour = itemView.findViewById<TextView>(R.id.tvItemScheduleHour)
        val tvItemScheduleSpeaker = itemView.findViewById<TextView>(R.id.tvItemScheduleSpeaker)
        val tvItemScheduleTag = itemView.findViewById<TextView>(R.id.tvItemScheduleTag)
    }

}