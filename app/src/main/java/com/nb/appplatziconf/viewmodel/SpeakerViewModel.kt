package com.nb.appplatziconf.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nb.appplatziconf.model.Speaker
import com.nb.appplatziconf.network.Callback
import com.nb.appplatziconf.network.FirestoreService
import java.lang.Exception

class SpeakerViewModel: ViewModel() {
    val firestoreService = FirestoreService()
    var listSpeakers: MutableLiveData<List<Speaker>> = MutableLiveData()
    var isLoading = MutableLiveData<Boolean>()

    fun refresh() {
        getSpeakersFromFirebase()
    }

    private fun getSpeakersFromFirebase() {
        firestoreService.getSpeakers(object : Callback<List<Speaker>> {
            override fun onSuccess(result: List<Speaker>?) {
                listSpeakers.postValue(result)
                processFinished()
            }

            override fun onFailed(exception: Exception) {
                processFinished()
            }
        })
    }

    private fun processFinished() {
        isLoading.value = true
    }
}