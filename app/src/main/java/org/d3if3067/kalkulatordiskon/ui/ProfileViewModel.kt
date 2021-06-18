package org.d3if3067.kalkulatordiskon.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.d3if3067.kalkulatordiskon.data.dataDiri
import org.d3if3067.kalkulatordiskon.network.ApiStatus
import org.d3if3067.kalkulatordiskon.network.DataApi

class ProfileViewModel : ViewModel() {

    private val data = MutableLiveData<List<dataDiri>>()
    private val status = MutableLiveData<ApiStatus>()

    init {
        retriveData()
    }
    private fun retriveData(){
        viewModelScope.launch {
            status.value = ApiStatus.Loading
            try{

                data.value = DataApi.service.getData()
                status.value = ApiStatus.Succes
            }catch (e: Exception){
                Log.d("MainViewModel", "Failure: ${e.message}")
                status.value = ApiStatus.Failure

            }
        }
    }
    //

    fun getData(): LiveData<List<dataDiri>> = data
    fun getStatus(): LiveData<ApiStatus> = status


}