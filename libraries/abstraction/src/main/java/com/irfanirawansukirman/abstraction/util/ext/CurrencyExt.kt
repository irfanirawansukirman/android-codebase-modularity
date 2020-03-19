package com.irfanirawansukirman.abstraction.util.ext

import java.text.NumberFormat
import java.util.*

fun convertCurrencyToRupiah(currency: Any): String {
    val localeId = Locale("id", "ID")
    val rupiahFormat = NumberFormat.getCurrencyInstance(localeId)

    return rupiahFormat.format(currency.toString().toDouble()).replace("Rp", "Rp ")
}