package com.mutakinngoding.disneycharacters.core.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mutakinngoding.disneycharacters.core.databinding.CharacterItemLayoutBinding
import com.mutakinngoding.disneycharacters.core.domain.entity.Character

class CharactersAdapter : ListAdapter<Character, CharactersAdapter.ViewHolder>(DiffUtilCallback()) {

    lateinit var onItemClickListener: (Character) -> Unit

    inner class ViewHolder(private val binding: CharacterItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(character: Character) {
            with(binding) {
                Glide
                    .with(binding.root.context)
                    .load(character.imageUrl)
                    .centerCrop()
                    .into(binding.picture)

                name.text = character.name
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            CharacterItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        val viewHolder = ViewHolder(view).apply {
            itemView.setOnClickListener {
                onItemClickListener(currentList[adapterPosition])
            }
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    class DiffUtilCallback : DiffUtil.ItemCallback<Character>() {
        override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem == newItem
        }

        override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
            return (oldItem.id == newItem.id) || (oldItem.isFavorite == newItem.isFavorite)
        }


    }


}