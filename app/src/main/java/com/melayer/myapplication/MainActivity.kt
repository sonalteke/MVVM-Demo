package com.melayer.myapplication

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.ProgressBar
import com.melayer.myapplication.adapter.RecyclerAdapter
import com.melayer.myapplication.models.NicePlaces
import com.melayer.myapplication.viewModels.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var recyclerAdapter: RecyclerAdapter? = null
    private var progressBar: ProgressBar? = null
    private var mainActivityViewModel: MainActivityViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainActivityViewModel =
            ViewModelProviders.of(this).get<MainActivityViewModel>(MainActivityViewModel::class.java!!)
        mainActivityViewModel?.init()

        mainActivityViewModel?.nicePlaces?.observe(this,
            Observer<ArrayList<NicePlaces>> { recyclerAdapter?.notifyDataSetChanged() })

        mainActivityViewModel?.isUpdating?.observe(this, Observer<Boolean> { aBoolean ->
            if (aBoolean!!) {
                showProgressBar()
            } else {
                hideProgressBar()
                recyclerView?.smoothScrollToPosition(mainActivityViewModel?.nicePlaces?.value!!.size)
            }
        })

        fab?.setOnClickListener {
            mainActivityViewModel?.addNewValue(
                NicePlaces("Washington", "https://i.imgur.com/ZcLLrkY.jpg")
            )
        }
        initRecyclerView()
    }

    private fun initRecyclerView() {
        recyclerAdapter = RecyclerAdapter(this, mainActivityViewModel?.nicePlaces?.value!!)
        val layoutManager = LinearLayoutManager(this)
        recyclerView?.layoutManager = layoutManager
        recyclerView?.adapter = recyclerAdapter
    }

    private fun showProgressBar() {
        progressBar?.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        progressBar?.visibility = View.INVISIBLE
    }
}