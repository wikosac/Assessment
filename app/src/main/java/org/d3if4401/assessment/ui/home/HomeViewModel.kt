package org.d3if4401.assessment.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if4401.assessment.db.HewanDao
import org.d3if4401.assessment.db.HewanEntity
import org.d3if4401.assessment.model.Hewan

class HomeViewModel(private val db: HewanDao) : ViewModel() {

    private val hasilHewan = MutableLiveData<Hewan?>()

    val data = db.getLastBmi()

    fun hasilInput(nama: String, latin: String, img: Int) {
        hasilHewan.value = Hewan(nama, latin, img)

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val dataBmi = HewanEntity(
                    nama = nama,
                    latin = latin,
                    img = img
                )
                db.insert(dataBmi)
            }
        }
    }

    fun getHasilHewan(): LiveData<Hewan?> = hasilHewan
}