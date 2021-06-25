package com.nb.appplatziconf.view.ui.fragment

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.nb.appplatziconf.R
import com.nb.appplatziconf.model.Speaker

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
        ivDetailSpeakerTwitter.setOnClickListener {
            var intent: Intent? = null
            try {
                // get the Twitter app if possible
                view.context.applicationContext.packageManager.getPackageInfo("com.twitter.android", 0)
                intent = Intent(Intent.ACTION_VIEW, Uri.parse("twitter://user?screen_name=${speaker.twitter}"))
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            } catch (e: Exception) {
                // no Twitter app, revert to browser
                intent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://twitter.com/${speaker.twitter}")
                )
            }
            this.startActivity(intent)
        }

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
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
    }
}