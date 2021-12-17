package com.satelliteship.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.satelliteship.R
import com.satelliteship.databinding.ItemMainBinding
import com.satelliteship.domain.model.Satellite


class MainAdapter(
    diffCallback: DiffUtil.ItemCallback<Satellite>,
    private val viewHolderBlock: MainViewHolder.() -> Unit
) : ListAdapter<Satellite, MainViewHolder>(diffCallback) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMainBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding).apply(viewHolderBlock)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val note = currentList[position]
        holder.onBind(note)
    }
}

class MainViewHolder(
    private val binding: ItemMainBinding
) : RecyclerView.ViewHolder(binding.root) {

    private lateinit var satellite: Satellite

    fun onBind(satellite: Satellite) {
        this.satellite = satellite
        val context = binding.root.context
        binding.title.text = satellite.name
        binding.description.text = satellite.active.toString()
        if (satellite.active) {
            binding.description.text = context.getString(R.string.active)
            binding.image.setImageDrawable(
                AppCompatResources.getDrawable(
                    context,
                    R.drawable.circle_green
                )
            )
        } else {
            binding.description.text = context.getString(R.string.passive)
            binding.image.setImageDrawable(
                AppCompatResources.getDrawable(
                    context,
                    R.drawable.circle_red
                )
            )
        }

    }

    fun onItemClicked(block: (Satellite) -> Unit) {
        binding.root.setOnClickListener { block(satellite) }
    }

}
