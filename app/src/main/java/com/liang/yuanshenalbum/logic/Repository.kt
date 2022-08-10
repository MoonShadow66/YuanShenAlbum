package com.liang.yuanshenalbum.logic

import androidx.lifecycle.liveData
import com.liang.yuanshenalbum.util.ImageResource
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

/**
 * 仓库类
 * 中转网络请求或数据库操作
 * 一般在这里进行一次线程转换
 */
object Repository {

    fun getImageUrlByName(name: String) = fire(Dispatchers.IO) {
        val list = ImageResource.getImageUrlByName(name)
        Result.success(list)
    }

    fun getRoleList() = fire(Dispatchers.IO) {
        val list = ImageResource.getRoleList()
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