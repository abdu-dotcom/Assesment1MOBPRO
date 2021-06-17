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
import org.d3if3067.kalkulatordiskon.data.HitungDiskon
import org.d3if3067.kalkulatordiskon.db.DiskonDao
import org.d3if3067.kalkulatordiskon.db.DiskonEntity

class DiskonViewModel(private val db: DiskonDao) : ViewModel() {


    private val hasilDiskon = MutableLiveData<HasilDiskon?>()

     fun hitungDiskon(biaya: String, diskon: String){

         val dataHitung = DiskonEntity(
                 biaya = biaya.toFloat(),
                 diskon = diskon.toFloat(),
         )

         hasilDiskon.value = HitungDiskon.Hitung(dataHitung);
         //kode mengirim nilai ke object di file HasilDiskon

         viewModelScope.launch {
             withContext(Dispatchers.IO){

                 db.insert(dataHitung);
             }
         }
    }
    
    fun gethasilDiskon() : LiveData<HasilDiskon?> = hasilDiskon
}