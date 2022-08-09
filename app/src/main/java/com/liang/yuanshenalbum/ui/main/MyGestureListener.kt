package com.liang.yuanshenalbum.ui.main

import android.content.Context
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.liang.yuanshenalbum.util.LogUtil

class MyGestureListener(val context: Context, private val drawerLayout: DrawerLayout) : GestureListener(context)  {

    override fun left(): Boolean {
        LogUtil.d("MyGestureListener","向左滑")
        return super.left()
    }

    override fun right(): Boolean {
        if (drawerLayout != null) {
            drawerLayout.openDrawer(GravityCompat.START)
        }
        LogUtil.d("MyGestureListener","向右滑")
        return super.right()
    }

}