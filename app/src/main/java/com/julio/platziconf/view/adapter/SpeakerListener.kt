package com.julio.platziconf.view.adapter

import com.julio.platziconf.model.Speaker

interface SpeakerListener {
    fun onSpeakerClick(conference: Speaker,position:Int)
}