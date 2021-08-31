package com.jcellomarcano.fixercurrency.repository

import com.jcellomarcano.fixercurrency.model.RatesX
import com.jcellomarcano.fixercurrency.model.Symbol

interface CurrencyRepository {

    suspend fun getSymbolList():List<Symbol>
    suspend fun getLastRates(symbol: String):List<RatesX>

}