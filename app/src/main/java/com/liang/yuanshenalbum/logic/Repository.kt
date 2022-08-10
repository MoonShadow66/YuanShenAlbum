package com.liang.yuanshenalbum.logic

import androidx.lifecycle.liveData
import com.liang.yuanshenalbum.util.ImageResource
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

object Repository {

    fun getImageUrlByName(name: String) = fire(Dispatchers.IO) {
        val list = ImageResource.getImageUrlByName(name)
        Result.success(list)
    }

    private fun <T> fire(context: CoroutineContext, block: suspend () -> Result<T>) =
        liveData<Result<T>>(context) {
            val result = try {
                block()
            } catch (e: Exception) {
                Result.failure<T>(e)
            }
            emit(result)
        }
}