package com.sopt.now

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.sopt.now.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val currentFragment = supportFragmentManager.findFragmentById(binding.fcvHome.id)
        if (currentFragment == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fcv_home, HomeFragment())
                .commit()
        }

        clickBottomNavigation()
        //로그인화면에서 받아온 id/pw 화면에 출력
        //getUserInfo()
    }
    private fun clickBottomNavigation() {
        binding.bnvHome.setOnItemSelectedListener{
            when (it.itemId) {
                R.id.menu_home-> {
                    replaceFragment(HomeFragment())
                    true
                }

                R.id.menu_search-> {
                    replaceFragment(SearchFragment())
                    true
                }

                R.id.menu_mypage-> {
                    replaceFragment(MyPageFragment())
                    true
                }

                else -> false
            }
        }
    }
    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fcv_home, fragment)
            .commit()
    }
//    private fun getUserInfo() {
//        binding.tvMainNick.text = intent.getStringExtra("nick")
//        binding.tvMainId.text = intent.getStringExtra("id")
//        binding.tvMainPw.text = intent.getStringExtra("pw")
//    }
}