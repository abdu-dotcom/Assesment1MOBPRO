package org.d3if3067.kalkulatordiskon.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if3067.kalkulatordiskon.db.DiskonDao

class HistoriViewModel(private val db: DiskonDao) : ViewModel() {
    val data = db.getLastHitung()

    fun hapusData() = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            db.deleteAll()
        }
    }
}