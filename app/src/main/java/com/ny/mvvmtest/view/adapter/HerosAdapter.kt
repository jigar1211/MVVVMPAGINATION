package com.ny.mvvmtest.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.ny.mvvmtest.BR
import com.ny.mvvmtest.model.data.MarvelModel
import com.ny.mvvmtest.viewmodel.HerosViewModel

class HerosAdapter(
    private val viewHeroes: Int,
    private val herosViewModel: HerosViewModel
) : RecyclerView.Adapter<HerosAdapter.HerosViewHolder>() {

    private var heroList: List<MarvelModel>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HerosViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<ViewDataBinding>(layoutInflater, viewType, parent, false)

        return HerosViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return heroList?.size ?: 0
    }

    override fun onBindViewHolder(holder: HerosViewHolder, position: Int) {
        holder.bind(herosViewModel, position)
    }

    fun setHeroList(heroList: List<MarvelModel>) {
        this.heroList = heroList
    }

     fun getLayoutIdForPosition(position: Int): Int {
        return viewHeroes
    }

    override fun getItemViewType(position: Int): Int {
        return getLayoutIdForPosition(position)
    }


    class HerosViewHolder(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(
        binding.root
    ) {

        fun bind(herosViewModel: HerosViewModel, position: Int) {
            herosViewModel.getImageURL(position)
            binding.setVariable(BR.position, position)
            binding.setVariable(BR.herosViewModel,herosViewModel)
            binding.executePendingBindings()

        }

    }

}
