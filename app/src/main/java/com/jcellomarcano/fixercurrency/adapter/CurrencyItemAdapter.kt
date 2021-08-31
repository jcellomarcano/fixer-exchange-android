package com.jcellomarcano.fixercurrency.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jcellomarcano.fixercurrency.R
import com.jcellomarcano.fixercurrency.model.Symbol
import kotlinx.android.synthetic.main.currency_item_view.view.*


class FixerItemAdapter(private val onClickListener: OnClickListener) :
    RecyclerView.Adapter<SymbolItemViewHolder>() {
    var symbolsList = listOf<Symbol>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onBindViewHolder(holder: SymbolItemViewHolder, position: Int) {
        holder.bind(symbolsList[position])
        holder.itemView.setOnClickListener {
            onClickListener.onClick(symbolsList[position])
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SymbolItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater
            .inflate(R.layout.currency_item_view, parent, false)
        return SymbolItemViewHolder(view)
    }

    override fun getItemCount() = symbolsList.size

    class OnClickListener(val clickListener: (symbol: Symbol) -> Unit) {
        fun onClick(symbol: Symbol) = clickListener(symbol)
    }
}

class SymbolItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val symbol = itemView.symbol
    private val exchangeValue = itemView.symbolNameTextView

    fun bind(data: Symbol) {
        symbol.text = data.symbol
        exchangeValue.text = data.name
    }
}