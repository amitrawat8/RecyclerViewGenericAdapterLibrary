package com.rvlibrary.ui


import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.amit.rvadapter.DataBindingActivity
import com.amit.rvadapter.ViewType
import com.amit.rvadapter.ViewTypeAdapter
import com.rvlibrary.BR
import com.rvlibrary.R
import com.rvlibrary.databinding.ActivityMainBinding
import com.rvlibrary.extension.lazyN
import org.koin.android.ext.android.inject

class MainActivity : DataBindingActivity<ActivityMainBinding>() {

    private val mainVM by inject<MainVM>()

    private val adapter by lazyN {

        //passing here data binding variable name
        ViewTypeAdapter<ViewType<*>>(
            onItemActionListener = mainVM,
            bmodel = BR.model,
            bactionItemListener = BR.actionItemListener,
            bPosition = BR.position
        )
    }

    override fun layoutId(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb.vm = mainVM
        setupAdapter()
        initObservers()
    }

    private fun setupAdapter() {
        vb.rvList.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        adapter.setList(mainVM.getList())//adding list
        vb.rvList.adapter = adapter//setting adapter
    }

    private fun initObservers() {
        mainVM.insertEvent.observe(this, Observer {
            adapter.insertElement(it.second, it.first)
        })
        mainVM.removeItemEvent.observe(this, Observer {
            adapter.removeElement(it)
        })
        mainVM.updateEvent.observe(this, Observer {
            adapter.updateElement(it.second, it.first)
        })
    }
}