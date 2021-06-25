package com.nb.appplatziconf.view.adapter

import com.nb.appplatziconf.model.Conference

interface ScheduleListener {

    fun onConferenceClicked(conference: Conference, position: Int)
}