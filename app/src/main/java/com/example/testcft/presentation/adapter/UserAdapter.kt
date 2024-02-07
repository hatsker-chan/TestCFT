package com.example.testcft.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.testcft.databinding.UserItemBinding
import com.example.testcft.domain.User
import com.squareup.picasso.Picasso

class UserAdapter (
    private val context: Context
) :
    ListAdapter<User, UserViewHolder>(DiffUserCallback) {

    lateinit var onItemClickListener: (user: User) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = UserItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = getItem(position)
        with(holder.binding){
            tvUserFullName.text = user.name
            Picasso.get().load(user.imageUrl).into(ivUserIcon)
            tvUserAddress.text = user.location
            tvUserTelephone.text = user.phone

            root.setOnClickListener {
                onItemClickListener(user)
            }
        }
    }
}