package com.jcellomarcano.fixercurrency.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jcellomarcano.fixercurrency.model.Currency
import com.jcellomarcano.fixercurrency.model.Symbol

class CurrencyViewModelFactory(private val currency: Currency, private val baseSymbol: Symbol) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CurrencyViewModel::class.java)) {
            return CurrencyViewModel(currency, baseSymbol) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}