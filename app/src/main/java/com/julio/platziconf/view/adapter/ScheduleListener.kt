package com.julio.platziconf.view.adapter

import com.julio.platziconf.model.Conference

interface ScheduleListener {
    fun onConferenceClick(conference: Conference,position:Int)
}