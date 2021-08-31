package com.jcellomarcano.fixercurrency.model

data class SymbolResponse(
    val success: Boolean,
    val symbols: Map<String, String>
)
