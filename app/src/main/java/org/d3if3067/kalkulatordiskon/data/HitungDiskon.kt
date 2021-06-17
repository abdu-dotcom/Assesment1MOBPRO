package org.d3if3067.kalkulatordiskon.data

import org.d3if3067.kalkulatordiskon.db.DiskonEntity

object HitungDiskon {

    fun Hitung(data: DiskonEntity): HasilDiskon {

        val biaya = data.biaya;
        val diskon = data.diskon;
        val nilaiDiskon = data.biaya * (diskon/100);
        val biayaSetelahDiskon = biaya- nilaiDiskon;

        return HasilDiskon(biaya,diskon,biayaSetelahDiskon,nilaiDiskon);
    }

}
