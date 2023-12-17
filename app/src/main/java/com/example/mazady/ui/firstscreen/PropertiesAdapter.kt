package com.example.mazady.ui.firstscreen

import android.R
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mazady.databinding.PropertiesItemBinding
import com.example.mazady.domain.model.Properties

class PropertiesAdapter(val onClickListener: DetailsClickListener) :
    ListAdapter<Properties, PropertiesAdapter.PropertiesViewHolder>(DetailsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PropertiesViewHolder {
        return PropertiesViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: PropertiesViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.binding.itemCategoryDropdown.setOnItemClickListener { adapterView, view, i, l ->
            val name=item.options?.get(i)?.name
            if (name != null) {
                item.slug?.let { onClickListener.onClick(it,name) }
            }
        }

    }

    class PropertiesViewHolder private constructor(val binding: PropertiesItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Properties) {
            binding.properties = item
            binding.itemCategoryDropdown.setAdapter(
                createAdapter(
                    binding.root.context,
                    item.options?.map { it?.name ?: "" } ?: emptyList()
                )
            )

            binding.executePendingBindings()
        }

        private fun createAdapter(context: Context, list: List<String>): ArrayAdapter<String> {
            return ArrayAdapter<String>(context, R.layout.select_dialog_item, list)
        }

        companion object {
            fun from(parent: ViewGroup): PropertiesViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = PropertiesItemBinding.inflate(layoutInflater, parent, false)
                return PropertiesViewHolder(binding)
            }
        }
    }

}


class DetailsDiffCallback : DiffUtil.ItemCallback<Properties>() {
    override fun areItemsTheSame(oldItem: Properties, newItem: Properties): Boolean {
        return oldItem.id == newItem.id

    }

    override fun areContentsTheSame(oldItem: Properties, newItem: Properties): Boolean {
        return oldItem == newItem
    }
}

class DetailsClickListener(val onClick: (slug: String,name:String) -> Unit)
