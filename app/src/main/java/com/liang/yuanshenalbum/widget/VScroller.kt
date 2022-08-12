package com.liang.yuanshenalbum.widget

import android.content.Context
import android.widget.Scroller

class VScroller(context: Context) : Scroller(context) {

    // 更改使用setCurrentItem切换viewPager的时间
    override fun startScroll(startX: Int, startY: Int, dx: Int, dy: Int, duration: Int) {
        // 切换时间设置为1秒
        super.startScroll(startX, startY, dx, dy, 1000)
    }

}