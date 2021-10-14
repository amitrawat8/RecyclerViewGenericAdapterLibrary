package com.amit.rvadapter


import android.os.Bundle
import com.amit.rvadapter.databinding.ActivityMainBinding

class MainActivity : DataBindingActivity<ActivityMainBinding>() {


    override fun layoutId(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }
}