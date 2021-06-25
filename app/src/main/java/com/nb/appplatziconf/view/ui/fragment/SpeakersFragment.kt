package com.nb.appplatziconf.view.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nb.appplatziconf.R
import com.nb.appplatziconf.model.Conference
import com.nb.appplatziconf.model.Speaker
import com.nb.appplatziconf.view.adapter.ScheduleAdapter
import com.nb.appplatziconf.view.adapter.SpeakerAdapter
import com.nb.appplatziconf.view.adapter.SpeakerListener
import com.nb.appplatziconf.viewmodel.ScheduleViewModel
import com.nb.appplatziconf.viewmodel.SpeakerViewModel

/**
 * A simple [Fragment] subclass.
 */
class SpeakersFragment : Fragment(), SpeakerListener {

    private lateinit var rvSpeakers: RecyclerView
    private lateinit var rlBase: View
    private lateinit var speakerAdapter: SpeakerAdapter
    private lateinit var viewModel: SpeakerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_speakers, container, false)

        viewModel = ViewModelProviders.of(this).get(SpeakerViewModel::class.java)
        viewModel.refresh()

        rvSpeakers = view.findViewById(R.id.rvSpeakers)
        rlBase = view.findViewById(R.id.rlBase)

        speakerAdapter = SpeakerAdapter(this)

        rvSpeakers.apply {
            layoutManager = GridLayoutManager(view.context, 2)
            adapter = speakerAdapter
        }

        //Observar los datos
        observeViewModel()

        return view
    }

    private fun observeViewModel() {
        viewModel.listSpeakers.observe(viewLifecycleOwner, Observer<List<Speaker>> { schedule ->
            speakerAdapter.updateData(schedule)
        })

        viewModel.isLoading.observe(viewLifecycleOwner, Observer<Boolean> {
            if (it != null)
                rlBase.visibility = View.INVISIBLE
        })
    }

    override fun onSpeakerClicked(speaker: Speaker, position: Int) {
        val bundle = bundleOf("speaker" to speaker)
        findNavController().navigate(R.id.speakersDetailFragmentDialog, bundle)
    }

}