package com.jammes.doginfo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jammes.doginfo.core.model.DogDomain
import com.jammes.doginfo.databinding.DogItemBinding

class DogListAdapter: RecyclerView.Adapter<DogListAdapter.ViewHolder>() {

    class ViewHolder(private val binding: DogItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(dogItem: DogDomain) {
            binding.DogNameTextView.text = dogItem.name
            Glide
                .with(itemView.context)
                .load(dogItem.image_link)
                .centerCrop()
                .into(binding.DogImageView)

            binding.root.setOnClickListener {
                val name = dogItem.name
                val action = DogSearchFragmentDirections.actionDogSearchFragmentToDogInfoFragment(name)

                itemView.findNavController().navigate(action)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DogItemBinding.inflate(layoutInflater, parent, false)

        return ViewHolder(binding)
    }

    fun updateDogsList(dogsList: List<DogDomain>) = asyncListDiffer.submitList(dogsList)

    override fun getItemCount(): Int = asyncListDiffer.currentList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(asyncListDiffer.currentList[position])
    }

    private val asyncListDiffer: AsyncListDiffer<DogDomain> = AsyncListDiffer(this, DiffCallBack)

    object DiffCallBack : DiffUtil.ItemCallback<DogDomain>() {
        override fun areItemsTheSame(oldItem: DogDomain, newItem: DogDomain): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: DogDomain, newItem: DogDomain): Boolean {
            return oldItem == newItem
        }

    }
}