package com.nocholla.livedata.viewmodel.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.nocholla.livedata.viewmodel.model.Blog
import com.nocholla.livedata.viewmodel.networking.RestApiService
import kotlinx.coroutines.*
import retrofit2.HttpException

class BlogRepository {

    // Coroutine - A concurrency design pattern that you can use on Android to simplify code that executes asynchronously.
    // CompletableJob - A job that can be completed using complete function. It is returned by Job() and SupervisorJob() constructor functions.

    // Dispatchers.IO - This dispatcher is optimized to perform disk or network I/O outside of the main thread.
    // Dispatchers.Main - Use this dispatcher to run a coroutine on the main Android thread. This should be used only for interacting with the UI and performing quick work.

    private var blogs = mutableListOf<Blog>()
    private var mutableLiveData = MutableLiveData<List<Blog>>()
    val completableJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.IO + completableJob)

    private val thisApiService by lazy {
        RestApiService.createService()
    }

    fun getMutableLiveData():MutableLiveData<List<Blog>> {
        coroutineScope.launch {
            val request = thisApiService.getBlogs()

            withContext(Dispatchers.Main) {
                try {

                    val response = request.await()
                    if (response.blog != null) {
                        blogs = response.blog as MutableList<Blog>
                        mutableLiveData.value = blogs
                    } else {
                        Log.d("DEBUG ERROR", "Response is null!")
                    }

                } catch (e: HttpException) {
                    Log.d("DEBUG ERROR", e.printStackTrace().toString())
                } catch (e: Throwable) {
                    Log.d("DEBUG ERROR", e.printStackTrace().toString())
                }
            }

        }

        return mutableLiveData;
    }

}