package com.nb.appplatziconf.view.ui.fragment

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.Toolbar

import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.nb.appplatziconf.R
import com.nb.appplatziconf.model.Conference
import java.text.SimpleDateFormat


/**
 * A simple [Fragment] subclass.
 */
class ScheduleDetailDialogFragment : DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.FullScreenDialogStyle)
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

        val toolbar:Toolbar = view.findViewById(R.id.toolbar)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            toolbar.navigationIcon = ContextCompat.getDrawable(view.context, R.drawable.ic_close)
            toolbar.setTitleTextColor(Color.WHITE)
            toolbar.setNavigationOnClickListener {
                dismiss()
            }
        }

        val conference = arguments?.getSerializable("conference") as Conference
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            toolbar.title = conference.title
        }

        val tvTitleConference: TextView = view.findViewById(R.id.tvTitlelConference)
        tvTitleConference.text = conference.title

        val tvDetailConferenceHour: TextView = view.findViewById(R.id.tvDetailConferenceHour)
        val pattern = "dd/MM/yyyy hh:mm a"
        val simpleDF = SimpleDateFormat(pattern)
        val date = simpleDF.format(conference.datetime)
        tvDetailConferenceHour.text = date

        val tvDetailConferenceSpeaker = view.findViewById<TextView>(R.id.tvDetailConferenceSpeaker)
        tvDetailConferenceSpeaker.text = conference.speaker
        val tvDetailConferenceTag = view.findViewById<TextView>(R.id.tvDetailConferenceTag)
        tvDetailConferenceTag.text = conference.tag
        val tvDetailConferenceDescription = view.findViewById<TextView>(R.id.tvDetailConferenceDescription)
        tvDetailConferenceDescription.text = conference.description
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT)
    }
}