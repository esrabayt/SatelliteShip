package com.satelliteship.domain.model

import androidx.recyclerview.widget.DiffUtil

data class Satellite(
    val id: Int,
    val active: Boolean,
    val name: String
) {
    companion object {
        val DIFF_CALLBACK
            get() = object : DiffUtil.ItemCallback<Satellite>() {
                override fun areItemsTheSame(
                    oldItem: Satellite,
                    newItem: Satellite
                ) = oldItem.id == newItem.id

                override fun areContentsTheSame(
                    oldItem: Satellite,
                    newItem: Satellite
                ) = oldItem == newItem
            }
    }
}
