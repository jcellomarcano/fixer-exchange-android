package com.jcellomarcano.fixercurrency.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jcellomarcano.fixercurrency.model.Currency
import com.jcellomarcano.fixercurrency.model.RatesX
import com.jcellomarcano.fixercurrency.model.Symbol
import com.jcellomarcano.fixercurrency.repository.CurrencyRepository
import com.jcellomarcano.fixercurrency.repository.FixerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CurrencyViewModel
    constructor(
        currency: Currency,
        baseSymbol: Symbol,
        private val currencyRepository: CurrencyRepository = FixerRepository,
        ) : ViewModel() {
    val currentCurrency = MutableLiveData<Currency>()
    val exchangeValue = MutableLiveData<String>()

    private val _rateList = MutableLiveData<List<RatesX>>()
    val rateList: LiveData<List<RatesX>> = _rateList

    init {
        currentCurrency.value = currency
        exchangeValue.value = ""
        loadRatesList(baseSymbol.symbol)
    }

    private fun loadRatesList(symbol: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _rateList.postValue(currencyRepository.getLastRates(symbol))
        }
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