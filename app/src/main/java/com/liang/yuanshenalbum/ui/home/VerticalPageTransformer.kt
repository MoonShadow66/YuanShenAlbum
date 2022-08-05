package com.liang.yuanshenalbum.ui.home

import android.view.View
import androidx.viewpager.widget.ViewPager

class VerticalPageTransformer : ViewPager.PageTransformer {

    override fun transformPage(page: View, position: Float) {
        /**
         *  0 当前界面
         *  -1 前一页
         *  1 后一页
         */
        if (position >= -1 && position <= 1) {
            page.translationX = (page.width * -position)
            val yPosition: Float = position * page.height
            page.translationY = yPosition
        }
    }

}