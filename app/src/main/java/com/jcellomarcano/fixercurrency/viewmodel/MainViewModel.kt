package com.jcellomarcano.fixercurrency.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jcellomarcano.fixercurrency.model.Symbol
import com.jcellomarcano.fixercurrency.repository.CurrencyRepository
import com.jcellomarcano.fixercurrency.repository.FixerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel
    constructor(
        private val currencyRepository: CurrencyRepository = FixerRepository,
    ): ViewModel() {

    val navigateToSelectedProperty = MutableLiveData<Symbol?>()
    private val _symbolList = MutableLiveData<List<Symbol>>()
    val symbolList: LiveData<List<Symbol>> = _symbolList

    init {
        loadSymbolList()
    }

    fun onSymbolSelected(symbol: Symbol) {
        navigateToSelectedProperty.value = symbol
    }

    fun displayCurrencyViewComplete() {
        navigateToSelectedProperty.value = null
    }

    private fun loadSymbolList() {
        viewModelScope.launch(Dispatchers.IO) {
            _symbolList.postValue(currencyRepository.getSymbolList())
        }
    }

}

