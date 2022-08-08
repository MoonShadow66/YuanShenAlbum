package com.liang.yuanshenalbum.ui.main

import android.widget.ImageView
import androidx.lifecycle.ViewModel
import com.liang.yuanshenalbum.MyApplication
import com.liang.yuanshenalbum.R
import com.liang.yuanshenalbum.logic.dao.Role

class MainViewModel : ViewModel() {

    val strList = ArrayList<String>()

    val viewList = ArrayList<ImageView>()

    val roleList = ArrayList<Role>()

}