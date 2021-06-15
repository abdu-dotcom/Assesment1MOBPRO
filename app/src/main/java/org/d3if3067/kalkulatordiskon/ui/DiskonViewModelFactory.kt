package org.d3if3067.kalkulatordiskon.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.d3if3067.kalkulatordiskon.db.DiskonDao
import java.lang.IllegalArgumentException

class DiskonViewModelFactory(
    private val db: DiskonDao
    ) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DiskonViewModel::class.java)){
            return DiskonViewModel(db) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
    }

