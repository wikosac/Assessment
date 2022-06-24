package org.d3if4401.assessment.ui.gallery

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if4401.assessment.R
import org.d3if4401.assessment.model.Hewan
import org.d3if4401.assessment.network.HewanApi

class GalleryViewModel : ViewModel() {

    private val data = MutableLiveData<List<Hewan>>()

    init {
        retrieveData()
    }

    // Data ini akan kita ambil dari server di langkah selanjutnya
    private fun retrieveData() {
        viewModelScope.launch (Dispatchers.IO) {
            try {
                data.postValue(HewanApi.service.getHewan())
            } catch (e: Exception) {
                Log.d("GalleryViewModel", "Failure: ${e.message}")
            }
        }
    }

    fun getData(): LiveData<List<Hewan>> = data
}