package com.jcellomarcano.fixercurrency.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jcellomarcano.fixercurrency.model.Currency

class CurrencyViewModel(currency: Currency) : ViewModel() {
    val currentCurrency = MutableLiveData<Currency>()
    val exchangeValue = MutableLiveData<String>()

    init {
        currentCurrency.value = currency
        exchangeValue.value = ""
    }

    fun onRequestCurrencyConversion(amount: String) {
        if (amount.isBlank()) {
            return
        }
        val result = String.format(
            "%.2f",
            amount.toDouble() * currentCurrency.value?.exchangeRate!!.toDouble()
        )
        exchangeValue.value = result + " ${currentCurrency.value?.symbol}"
    }
}