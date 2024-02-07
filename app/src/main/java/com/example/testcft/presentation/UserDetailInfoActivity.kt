package com.example.testcft.presentation

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.testcft.databinding.ActivityUserDetailInfoBinding
import com.example.testcft.domain.User
import com.squareup.picasso.Picasso

class UserDetailInfoActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityUserDetailInfoBinding.inflate(layoutInflater)
    }

    private val viewModel by lazy {
        ViewModelProvider(this)[UserViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val userLogin = getUserLogin()

        viewModel.getUser(userLogin).observe(this) {
            with(binding) {
                Picasso.get().load(it.imageUrl).into(ivUserIcon)
                tvUserFullName.text = it.name
                tvUserGender.text = it.gender
                tvUserAddress.text = getSpannableText(it.location)
                tvUserAddress.setOnClickListener {_ ->
                    val uri = Uri.parse("http://maps.google.co.in/maps?q=${it.location}")
                    val intent =  Intent(Intent.ACTION_VIEW, uri)
                    startActivity(intent)
                }
                tvUserAge.text = it.age.toString()
                tvUserEmail.text = getSpannableText(it.email)
                tvUserEmail.setOnClickListener { _ ->
                    val intent = Intent(Intent.ACTION_SENDTO)
                    intent.data = Uri.parse("mailto:${it.email}")
                    startActivity(intent)
                }

                tvUserPhone.text = getSpannableText(it.phone)
                tvUserPhone.setOnClickListener { _ ->
                    val uri = Uri.parse("tel:${it.phone}")
                    val intent = Intent(Intent.ACTION_DIAL, uri)
                    startActivity(intent)
                }

                tvUserLogin.text = it.login
            }
        }

    }

    private fun getSpannableText(text: String): SpannableString {
        val mSpannableString = SpannableString(text)
        mSpannableString.setSpan(UnderlineSpan(), 0, mSpannableString.length, 0)
        return mSpannableString
    }


    private fun getUserLogin(): String {
        val intent = this.intent
        return intent.getStringExtra(EXTRA_USER_LOGIN) ?: throw IllegalArgumentException()
    }

    companion object {
        fun newIntent(context: Context, user: User): Intent {
            val intent = Intent(
                context,
                UserDetailInfoActivity::class.java
            )
            intent.putExtra(EXTRA_USER_LOGIN, user.login)
            return intent
        }

        private const val EXTRA_USER_LOGIN = "user_login"
    }
}