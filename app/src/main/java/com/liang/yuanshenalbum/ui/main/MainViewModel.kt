package com.liang.yuanshenalbum.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.liang.yuanshenalbum.logic.Repository
import com.liang.yuanshenalbum.logic.model.Role

class MainViewModel : ViewModel() {

    private val getImageLiveData = MutableLiveData<String>()
    private val getRoleLiveData = MutableLiveData<Any?>()

    val strList = ArrayList<String>()
    val roleList = ArrayList<Role>()

    val imageLiveData = Transformations.switchMap(getImageLiveData) { name ->
        Repository.getImageUrlByName(name)
    }

    val roleLiveData = Transformations.switchMap(getRoleLiveData){
        Repository.getRoleList()
    }

    fun getImage(name: String) {
        getImageLiveData.value = name
    }

    fun getRole() {
        getRoleLiveData.value = getRoleLiveData.value
    }

}