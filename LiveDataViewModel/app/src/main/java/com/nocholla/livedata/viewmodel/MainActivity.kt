package com.nocholla.livedata.viewmodel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.nocholla.livedata.viewmodel.adapter.BlogAdapter
import com.nocholla.livedata.viewmodel.model.Blog
import com.nocholla.livedata.viewmodel.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var mainViewModel: MainViewModel? = null
    var blogAdapter: BlogAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        getTopBlogs()

    }

    private fun getTopBlogs() {

        mainViewModel?.allBlog?.observe(this, Observer { blogList ->
            if (blogList != null) {
                prepareRecyclerView(blogList)
            }
        })

    }

    private fun prepareRecyclerView(blogList: List<Blog>) {

        blogAdapter = BlogAdapter(blogList)

        blogRecyclerView.layoutManager = LinearLayoutManager(this)
        blogRecyclerView.itemAnimator = DefaultItemAnimator()
        blogRecyclerView.adapter = blogAdapter

    }

}

