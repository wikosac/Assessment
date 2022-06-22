package org.d3if4401.hitungbmi.ui.histori

import androidx.lifecycle.ViewModel
import org.d3if4401.assessment.db.HewanDao

class HistoriViewModel(db: HewanDao) : ViewModel() {
    val data = db.getLastBmi()
}