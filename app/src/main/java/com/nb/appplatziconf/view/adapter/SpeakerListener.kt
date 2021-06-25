package com.nb.appplatziconf.view.adapter

import com.nb.appplatziconf.model.Speaker

interface SpeakerListener {

    fun onSpeakerClicked(speaker: Speaker, position: Int)
}