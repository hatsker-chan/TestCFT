package com.example.testcft.presentation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.testcft.databinding.ActivityMainBinding
import com.example.testcft.presentation.adapter.UserAdapter

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel by lazy {
        ViewModelProvider(this)[UserViewModel::class.java]
    }

    private val userListAdapter by lazy {
        UserAdapter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            viewModel.refreshData()
        }
        setupRv()
        viewModel.users.observe(this) {
            userListAdapter.submitList(it)
        }
        viewModel.isConnectError.observe(this){
            if (it){
                Toast.makeText(this, "Connection error", Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.isLoading.observe(this){
            if (it){
                binding.progressBar.visibility = View.VISIBLE
                binding.button.isEnabled = false
            } else {
                binding.progressBar.visibility = View.INVISIBLE
                binding.button.isEnabled = true
            }
        }
    }


    private fun setupRv(){
        binding.rvNoteList.adapter = userListAdapter

        userListAdapter.onItemClickListener = {
            val intent = UserDetailInfoActivity.newIntent(this, it)
            startActivity(intent)
        }
    }
}