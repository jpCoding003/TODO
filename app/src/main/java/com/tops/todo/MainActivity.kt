package com.tops.todo

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.tabs.TabLayoutMediator
import com.tops.todo.adapter.ViewPagerAdapter
import com.tops.todo.databinding.ActivityMainBinding
import com.tops.todo.fragment.HomeFragment
import com.tops.todo.fragment.SecondFragment
import com.tops.todo.fragment.ThirdFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewpageradapter: ViewPagerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        setSupportActionBar(binding.maintoolbar)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val listoffragments = listOf(HomeFragment() , SecondFragment(), ThirdFragment())

        viewpageradapter = ViewPagerAdapter(listoffragments,supportFragmentManager,lifecycle)

        binding.viewPager.adapter = viewpageradapter

        TabLayoutMediator( binding.tabLayout, binding.viewPager){
            tab, viewpager->
            tab.text = when(viewpager){

                0-> "Home"
                1-> "Second"
                2-> "third"
                else -> " "
            }
        }.attach()
    }
}