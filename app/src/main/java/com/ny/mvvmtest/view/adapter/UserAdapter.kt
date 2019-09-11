package com.ny.mvvmtest.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ny.mvvmtest.BR
import com.ny.mvvmtest.R
import com.ny.mvvmtest.databinding.ViewUserBinding
import com.ny.mvvmtest.model.data.DataModel
import com.ny.mvvmtest.viewmodel.UserViewModel



class UserAdapter(userViewModel: UserViewModel) :
    PagedListAdapter<DataModel, UserAdapter.UserViewHolder>(userViewModel.CALLBACK) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
//        val binding =
//            DataBindingUtil.inflate<ViewDataBinding>(layoutInflater, viewType, parent, false)

       val binding:ViewUserBinding =  DataBindingUtil.inflate(layoutInflater,  R.layout.view_user, parent, false)
        return UserViewHolder(binding)
    }


    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(getItem(position))
    }



    class UserViewHolder(private val binding: ViewUserBinding) : RecyclerView.ViewHolder(
        binding.root
    ) {

        fun bind(
            userViewModel: DataModel?
        ){
            binding.setVariable(BR.userViewModel,userViewModel)
            binding.executePendingBindings()
        }
    }
}