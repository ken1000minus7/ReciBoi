package com.miniweebs.reciboi.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.miniweebs.reciboi.R
import com.miniweebs.reciboi.data.api.Areas
import com.miniweebs.reciboi.data.api.Category
import com.miniweebs.reciboi.data.api.Country

class CountryAdapter(val context: Context,val countryList: MutableList<Country>,  private val listener: ListenersCountry):RecyclerView.Adapter<CountryAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val country: TextView = itemView.findViewById(R.id.tv_name_country)
        init {
            country.setOnClickListener {
                listener.onItemClicked(countryList[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
     return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.card_country,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val entity = countryList[position]
       holder.country.text = entity.strArea
    }

    override fun getItemCount() = countryList.size

}
interface ListenersCountry{
    fun onItemClicked(category: Country)
}
