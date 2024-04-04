package com.sopt.now

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sopt.now.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //로그인화면에서 받아온 id/pw 화면에 출력
        getUser()
    }
    private fun getUser() {
        binding.tvMainNick.text = intent.getStringExtra("nick")
        binding.tvMainId.text = intent.getStringExtra("id")
        binding.tvMainPw.text = intent.getStringExtra("pw")
    }
}