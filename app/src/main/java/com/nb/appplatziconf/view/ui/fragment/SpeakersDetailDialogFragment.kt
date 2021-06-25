package com.nb.appplatziconf.view.ui.fragment

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.nb.appplatziconf.R
import com.nb.appplatziconf.model.Conference
import com.nb.appplatziconf.model.Speaker
import java.text.SimpleDateFormat

/**
 * A simple [Fragment] subclass.
 */
class SpeakersDetailDialogFragment : DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullScreenDialogStyle)
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

        val toolbar: Toolbar = view.findViewById(R.id.toolbar)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            toolbar.navigationIcon = ContextCompat.getDrawable(view.context, R.drawable.ic_close)
            toolbar.setTitleTextColor(Color.WHITE)
            toolbar.setNavigationOnClickListener {
                dismiss()
            }
        }

        val speaker = arguments?.getSerializable("speaker") as Speaker
        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            toolbar.title = speaker.category
        }*/

        val ivDetailSpeakerImage: ImageView = view.findViewById(R.id.ivDetailSpeakerImage)
        Glide.with(view.context)
            .load(speaker.image)
            .apply(RequestOptions.circleCropTransform())
            .into(ivDetailSpeakerImage)
        val ivDetailSpeakerTwitter: ImageView = view.findViewById(R.id.ivDetailSpeakerTwitter)
        ivDetailSpeakerTwitter.setOnClickListener(View.OnClickListener { println("CLICK") })
        //ivDetailSpeakerImage.text = conference.title

        val tvDetailSpeakerName: TextView = view.findViewById(R.id.tvDetailSpeakerName)
        tvDetailSpeakerName.text = speaker.name

        val tvDetailSpeakerJobTitle = view.findViewById<TextView>(R.id.tvDetailSpeakerJobTitle)
        tvDetailSpeakerJobTitle.text = speaker.jobtitle
        val tvDetailSpeakerWorkplace = view.findViewById<TextView>(R.id.tvDetailSpeakerWorkplace)
        tvDetailSpeakerWorkplace.text = speaker.workplace
        val tvDetailSpeakerBio = view.findViewById<TextView>(R.id.tvDetailSpeakerBio)
        tvDetailSpeakerBio.text = speaker.biography
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT)
    }
}