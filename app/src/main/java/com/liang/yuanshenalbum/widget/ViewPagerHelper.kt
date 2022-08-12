package com.liang.yuanshenalbum.widget

import androidx.viewpager.widget.ViewPager

class ViewPagerHelper(private val viewPager: ViewPager) {

    init {
        val scroller = VScroller(viewPager.context)
        val cl = ViewPager::class.java
        try {
            //利用反射设置mScroller域为自己定义的WScroller，这里的命名（‘mScroller’）不能随意改
            val field = cl.getDeclaredField("mScroller")
            field.isAccessible = true
            field[viewPager] = scroller
        } catch (e: NoSuchFieldException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        }
    }

}