package com.nocholla.livedata.viewmodel.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.nocholla.livedata.viewmodel.model.Blog
import com.nocholla.livedata.viewmodel.repository.BlogRepository

class MainViewModel : ViewModel() {

    private val blogRepository= BlogRepository()
    val allBlog: LiveData<List<Blog>> get() = blogRepository.getMutableLiveData()

    override fun onCleared() {
        super.onCleared()
        blogRepository.completableJob.cancel()
    }

}