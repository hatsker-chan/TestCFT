package com.example.testcft.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.testcft.domain.User

object DiffUserCallback: DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.login == newItem.login
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }

}