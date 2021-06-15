package org.d3if3067.kalkulatordiskon.ui

import android.text.TextUtils
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.invoke
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if3067.kalkulatordiskon.data.HasilDiskon
import org.d3if3067.kalkulatordiskon.db.DiskonDao
import org.d3if3067.kalkulatordiskon.db.DiskonEntity

class DiskonViewModel(private val db: DiskonDao) : ViewModel() {

    val data = db.getLastHitung();
    private val hasilDiskon = MutableLiveData<HasilDiskon?>()

     fun hitungDiskon(biaya: String, diskon: String){
         val biaya = biaya.toFloat();
         val diskon = diskon.toFloat();
        val nilaiDiskon = biaya * (diskon/100)
        val biayaSetelahDiskon = biaya- nilaiDiskon

         //kode mengirim nilai ke object di file HasilDiskon
        hasilDiskon.value = HasilDiskon(biaya,diskon, biayaSetelahDiskon, nilaiDiskon);

         viewModelScope.launch {
             withContext(Dispatchers.IO){
                 val dataHitung = DiskonEntity(
                     biaya = biaya,
                     diskon = diskon,
                     nilaiDiskon = nilaiDiskon,
                     biayaSetelahDiskon = biayaSetelahDiskon
                 )
                 db.insert(dataHitung);
             }
         }
    }
    
    fun gethasilDiskon() : LiveData<HasilDiskon?> = hasilDiskon
}